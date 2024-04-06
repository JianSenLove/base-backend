package com.jason.mirageledger.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.shop.entity.Category;
import com.jason.mirageledger.shop.entity.Product;
import com.jason.mirageledger.shop.service.CategoryService;
import com.jason.mirageledger.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/mirageLedger/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${baseImagePath}")
    private String baseImagePath;

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
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.trim().isEmpty()) {
            queryWrapper.like(Product::getName, name);
        }
        queryWrapper.orderByDesc(Product::getUpdateTime);

        Page<Product> page = productService.page(new Page<>(currentPage, size), queryWrapper);

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

}
