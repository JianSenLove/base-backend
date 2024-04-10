package com.jason.mirageledger.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.shop.entity.ViewHistory;
import com.jason.mirageledger.shop.service.ViewHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (history != null) {
            viewHistory.setCount(history.getCount() + 1);
            viewHistoryService.updateById(history);
        } else {
            viewHistory.setCount(1);
            viewHistoryService.saveOrUpdate(viewHistory);
        }
        return viewHistory;
    }

    // 删除浏览记录
    @DeleteMapping("/{id}")
    public void deleteViewHistory(@PathVariable String id) {
        viewHistoryService.removeById(id);
    }

    // 获取用户的浏览记录列表
    @GetMapping("")
    public Page<ViewHistory> getUserViewHistory(@RequestParam(value = "page", defaultValue = "1") int currentPage,
                                                @RequestParam(value = "rows", defaultValue = "10") int size) {
        String userId = AuthenticationUtil.getAuthentication();
        LambdaQueryWrapper<ViewHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ViewHistory::getUserId, userId);
        return viewHistoryService.page(new Page<>(currentPage, size), queryWrapper);
    }
}

