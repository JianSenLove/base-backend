package com.jason.mirageledger.shop.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.shop.entity.Category;
import com.jason.mirageledger.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@RestController
@RequestMapping("/mirageLedger/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 创建新的类别
    @PostMapping("")
    public Category createCategory(@RequestBody Category category) {
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(category.getName()), "类别名称不能为空", HttpStatus.BAD_REQUEST);

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
            @RequestParam(value = "rows", defaultValue = "10") int size) {
        return categoryService.page(new Page<>(currentPage, size));
    }

}

