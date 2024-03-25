package com.jason.mirageledger.shop.controller;

import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.shop.entity.Cart;
import com.jason.mirageledger.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

@RestController
@RequestMapping("/mirageLedger/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 添加商品到购物车
    @PostMapping("")
    public Cart addProductToCart(@RequestBody Cart cart) {

        cartService.save(cart);
        return cart;
    }

    // 更新购物车中的商品数量
    @PutMapping("/{id}")
    public Cart updateProductQuantity(@PathVariable String id, @RequestBody Cart cart) {
        // 验证购物车项是否存在
        RestPreconditions.checkParamArgument(
                cartService.lambdaQuery().eq(Cart::getId, id).count() > 0,
                "购物车项不存在", HttpStatus.NOT_FOUND);

        // 更新购物车项
        cart.setId(id);
        cartService.updateById(cart);

        return cartService.getById(id);
    }

    // 从购物车中移除商品
    @DeleteMapping("/{id}")
    public void removeProductFromCart(@PathVariable String id) {
        cartService.removeById(id);
    }

    // 获取用户的购物车详情
    @GetMapping("")
    public Page<Cart> getUserCartPage(
            @RequestParam(value = "page", defaultValue = "1") int currentPage,
            @RequestParam(value = "rows", defaultValue = "10") int size) {
        String userId = AuthenticationUtil.getAuthentication();
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUserId, userId);

        return cartService.page(new Page<>(currentPage, size), queryWrapper);
    }
}

