package com.jason.mirageledger.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.shop.entity.ViewHistory;
import com.jason.mirageledger.shop.service.ViewHistoryService;
import com.jason.mirageledger.shop.mapper.ViewHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 
* @description 针对表【mirageledger_view_history(用户商品浏览记录表)】的数据库操作Service实现
* @createDate 2024-03-26 00:00:33
*/
@Service
public class ViewHistoryServiceImpl extends ServiceImpl<ViewHistoryMapper, ViewHistory>
    implements ViewHistoryService{

}




