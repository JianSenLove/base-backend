package com.jason.mirageledger.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.shop.entity.Order;
import com.jason.mirageledger.shop.service.OrderService;
import com.jason.mirageledger.shop.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 
* @description 针对表【mirageledger_order(订单表)】的数据库操作Service实现
* @createDate 2024-03-26 00:00:33
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




