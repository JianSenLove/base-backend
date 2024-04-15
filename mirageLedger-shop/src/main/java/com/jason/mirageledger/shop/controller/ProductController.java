package com.jason.mirageledger.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.CustomException;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.shop.entity.Category;
import com.jason.mirageledger.shop.entity.Product;
import com.jason.mirageledger.shop.entity.PythonClassificationResponse;
import com.jason.mirageledger.shop.service.CategoryService;
import com.jason.mirageledger.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mirageLedger/v1/product")
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductService productService;

    @Value("${baseImagePath}")
    private String baseImagePath;

    @Value("${pythonUrl}")
    private String pythonUrl;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public Product createDatabaseInfo(@RequestBody Product product) {
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(product.getName()), "商品名称不能为空", HttpStatus.BAD_REQUEST);
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(product.getCategoryId()), "商品类别不能为空", HttpStatus.BAD_REQUEST);
        RestPreconditions.checkParamArgument(product.getPrice() != null && product.getPrice() > 0, "价格不能为空", HttpStatus.BAD_REQUEST);
        RestPreconditions.checkParamArgument(product.getStock() != null && product.getStock() > 0, "库存不能为空", HttpStatus.BAD_REQUEST);

        RestPreconditions.checkParamArgument(
                productService.lambdaQuery().eq(Product::getName, product.getName()).count() == 0,
                "商品名称已存在", HttpStatus.BAD_REQUEST);

        productService.save(product);
        return product;
    }


    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        // 验证商品是否存在
        RestPreconditions.checkParamArgument(
                productService.lambdaQuery().eq(Product::getId, id).count() > 0,
                "商品信息不存在", HttpStatus.NOT_FOUND);

        // 商品名称变更的情况
        if (StringUtils.isNotBlank(product.getName())) {
            RestPreconditions.checkParamArgument(
                    productService.lambdaQuery()
                            .eq(Product::getName, product.getName())
                            .ne(Product::getId, id)
                            .count() == 0,
                    "商品名称已存在", HttpStatus.BAD_REQUEST);
        } else {
            product.setName(null);
        }

        // 更新其他字段，这里假设Product实体包含了相应的字段
        product.setId(id);
        productService.updateById(product);

        Product product2 = productService.getById(id);

        product2.setImage(baseImagePath + id + ".jpg");

        product2.setCategoryName(categoryService.getById(product2.getCategoryId()).getName());

        return product2;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.removeById(id);
    }

    @GetMapping("")
    public Page<Product> getProductsPage(
            @RequestParam(value = "page", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "rows", defaultValue = "10") Integer size,
            @RequestParam(value = "name", required = false) String name) {

        List<Product> externalProducts = new ArrayList<>();
        // 当name为空时，才调用外部接口
        if (StringUtils.isNotBlank(name)) {
            try {
                Product[] productArray = restTemplate.getForObject(pythonUrl + "get_recommend_products", Product[].class);
                externalProducts = Arrays.asList(productArray != null ? productArray : new Product[0]);
            } catch (HttpClientErrorException | HttpServerErrorException exception) {
                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR,"调用外部接口错误:" + exception.getMessage());
            } catch (RestClientException e) {
                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR,"调用外部接口失败:" + e.getMessage());
            }
        }

        Page<Product> page;
        if (!externalProducts.isEmpty()) {
            List<String> externalProductIds = externalProducts.stream().map(Product::getId).collect(Collectors.toList());
            LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(name)) {
                queryWrapper.like(Product::getName, name);
            }
            // 从数据库查询时排除外部请求获取的数据部分
            queryWrapper.notIn(Product::getId, externalProductIds);
            queryWrapper.orderByDesc(Product::getUpdateTime);

            // 从数据库查询剩余的产品以填充分页
            long total = productService.count(queryWrapper);
            List<Product> dbProducts = productService.list(queryWrapper);

            // 合并外部产品和数据库产品列表
            List<Product> allProducts = new ArrayList<>(externalProducts);
            allProducts.addAll(dbProducts);

            // 创建手动分页
            page = new Page<>(currentPage, size, total + externalProducts.size());
            page.setRecords(allProducts);
        } else {
            // 如果外部接口返回的数据为空，或者`name`参数不为空，则正常从数据库查询并进行分页
            LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
            if (name != null && !name.trim().isEmpty()) {
                queryWrapper.like(Product::getName, name);
            }
            queryWrapper.orderByDesc(Product::getUpdateTime);
            page = productService.page(new Page<>(currentPage, size), queryWrapper);
        }

        page.getRecords().forEach(product -> {
            String imagePath = baseImagePath + product.getId() + ".jpg";
            product.setImage(imagePath);
            Category category = categoryService.getById(product.getCategoryId());
            if (category != null) {
                product.setCategoryName(category.getName());
            }
        });

        return page;
    }

    @GetMapping("/{id}")
    public Product getProductDetail(@PathVariable String id) {
        Product product = productService.getById(id);
        RestPreconditions.checkParamArgument(product != null, "商品信息未找到", HttpStatus.NOT_FOUND);
        String imagePath = baseImagePath + product.getId() + ".jpg";
        product.setImage(imagePath);
        return product;
    }

    @PostMapping("/classify")
    public String classify(@RequestParam("image") MultipartFile imageFile) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", getByteArrayResource(imageFile));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<PythonClassificationResponse> response = restTemplate.exchange(
                    pythonUrl + "/flowerclassify",
                    HttpMethod.POST,
                    requestEntity,
                    PythonClassificationResponse.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String name = response.getBody().getResult_data().getName();
                // 解码可能的Unicode字符
                String decodedName = java.net.URLDecoder.decode(name, StandardCharsets.UTF_8.name());
                return decodedName;
            } else {
                return "错误";
            }
        } catch (IOException e) {
            // 处理异常
        }
        return "错误";
    }

    private Resource getByteArrayResource(MultipartFile imageFile) throws IOException {
        return new ByteArrayResource(imageFile.getBytes()) {
            @Override
            public String getFilename() {
                return imageFile.getOriginalFilename(); // 这里要设置原始文件名
            }
        };
    }


}
