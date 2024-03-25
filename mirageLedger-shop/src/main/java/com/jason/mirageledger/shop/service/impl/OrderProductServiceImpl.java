package com.jason.mirageledger.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.shop.entity.OrderProduct;
import com.jason.mirageledger.shop.service.OrderProductService;
import com.jason.mirageledger.shop.mapper.OrderProductMapper;
import org.springframework.stereotype.Service;

/**
* @author 
* @description 针对表【mirageledger_order_product(订单商品关联表)】的数据库操作Service实现
* @createDate 2024-03-26 00:00:33
*/
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct>
    implements OrderProductService{

}




