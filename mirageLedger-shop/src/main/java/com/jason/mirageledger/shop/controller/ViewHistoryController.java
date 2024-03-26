package com.jason.mirageledger.shop.controller;

import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.shop.entity.ViewHistory;
import com.jason.mirageledger.shop.service.ViewHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/mirageLedger/v1/viewHistory")
public class ViewHistoryController {

    @Autowired
    private ViewHistoryService viewHistoryService;

    // 添加浏览记录
    @PostMapping("")
    public ViewHistory addViewHistory(@RequestBody ViewHistory viewHistory) {
        String userId = AuthenticationUtil.getAuthentication();
        viewHistory.setUserId(userId);
        // 这里可以添加逻辑来检查相同用户对同一商品的浏览记录是否已存在，如果存在，则计数+1
        ViewHistory history = viewHistoryService.getOne(new LambdaQueryWrapper<ViewHistory>()
                .eq(ViewHistory::getUserId, userId)
                .eq(ViewHistory::getProductId, viewHistory.getProductId()));
        viewHistoryService.saveOrUpdate(viewHistory);
        return viewHistory;
    }

    // 更新浏览记录，主要是浏览次数
    @PutMapping("/{id}")
    public ViewHistory updateViewHistory(@PathVariable String id, @RequestBody ViewHistory viewHistory) {
        // 检查浏览记录是否存在
        ViewHistory existingViewHistory = viewHistoryService.getById(id);
        if (existingViewHistory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "浏览记录未找到");
        }

        existingViewHistory.setCount(viewHistory.getCount()); // 假设ViewHistory实体中有一个计数器字段
        viewHistoryService.updateById(existingViewHistory);

        return existingViewHistory;
    }

    // 删除浏览记录
    @DeleteMapping("/{id}")
    public void deleteViewHistory(@PathVariable String id) {
        viewHistoryService.removeById(id);
    }

    // 获取用户的浏览记录列表
    @GetMapping("/user/{userId}")
    public Page<ViewHistory> getUserViewHistory(@PathVariable String userId,
                                                @RequestParam(value = "page", defaultValue = "1") int currentPage,
                                                @RequestParam(value = "rows", defaultValue = "10") int size) {
        LambdaQueryWrapper<ViewHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ViewHistory::getUserId, userId);
        return viewHistoryService.page(new Page<>(currentPage, size), queryWrapper);
    }
}

