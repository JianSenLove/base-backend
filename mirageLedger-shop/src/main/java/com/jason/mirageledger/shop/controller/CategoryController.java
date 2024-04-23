package com.jason.mirageledger.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.shop.entity.Category;
import com.jason.mirageledger.shop.entity.Product;
import com.jason.mirageledger.shop.service.CategoryService;
import com.jason.mirageledger.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@RestController
@RequestMapping("/mirageLedger/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Value("${baseImagePath}")
    private String baseImagePath;

    // 创建新的类别
    @PostMapping("")
    public Category createCategory(@RequestBody Category category) {
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(category.getName()), "类别名称不能为空", HttpStatus.BAD_REQUEST);
        // 判断类别名称是否已存在
        RestPreconditions.checkParamArgument(
                categoryService.lambdaQuery().eq(Category::getName, category.getName()).count() == 0,
                "类别名称已存在", HttpStatus.BAD_REQUEST);
        categoryService.save(category);
        return category;
    }

    // 更新类别信息
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable String id, @RequestBody Category category) {
        // 验证类别是否存在
        RestPreconditions.checkParamArgument(
                categoryService.lambdaQuery().eq(Category::getId, id).count() > 0,
                "类别不存在", HttpStatus.NOT_FOUND);
        // 判断类别名称是否已存在且不为当前类别ID
        RestPreconditions.checkParamArgument(
                categoryService.lambdaQuery().eq(Category::getName, category.getName()).ne(Category::getId, id).count() == 0,
                "类别名称已存在", HttpStatus.BAD_REQUEST);
        category.setId(id);
        categoryService.updateById(category);

        return categoryService.getById(id);
    }

    // 删除类别
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable String id) {
        categoryService.removeById(id);
    }

    // 获取单个类别的详细信息
    @GetMapping("/{id}")
    public Category getCategoryDetail(@PathVariable String id) {
        Category category = categoryService.getById(id);
        RestPreconditions.checkParamArgument(category != null, "类别未找到", HttpStatus.NOT_FOUND);
        return category;
    }

    // 分页获取类别列表
    @GetMapping("")
    public Page<Category> getCategoriesPage(
            @RequestParam(value = "page", defaultValue = "1") int currentPage,
            @RequestParam(value = "rows", defaultValue = "10") int size,
            @RequestParam(value = "name", required = false) String name) {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<Category>().orderByDesc(Category::getUpdateTime);
        if (StringUtils.isNotBlank(name)) {
            categoryLambdaQueryWrapper.like(Category::getName, name);
        }
        Page<Category> categoryPage = categoryService.page(new Page<>(currentPage, size), categoryLambdaQueryWrapper);
        // 查出分类下的商品,并为每个商品配置image
        categoryPage.getRecords().forEach(category -> {
            Page<Product> productPage = productService.lambdaQuery()
                    .eq(Product::getCategoryId, category.getId())
                    .page(new Page<>(1, 10));
            category.setProducts(productPage.getRecords());
            category.getProducts().forEach(product -> {
                product.setImage(baseImagePath + product.getId() + ".jpg");
            });
        });
        return categoryPage;
    }

}

