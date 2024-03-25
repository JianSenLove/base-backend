package com.jason.mirageledger.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.shop.entity.Cart;
import com.jason.mirageledger.shop.service.CartService;
import com.jason.mirageledger.shop.mapper.CartMapper;
import org.springframework.stereotype.Service;

/**
* @author 
* @description 针对表【mirageledger_cart(用户购物车表)】的数据库操作Service实现
* @createDate 2024-03-26 00:00:33
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{

}




