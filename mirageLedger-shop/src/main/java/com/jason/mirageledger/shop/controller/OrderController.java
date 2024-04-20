package com.jason.mirageledger.shop.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.shop.entity.Order;
import com.jason.mirageledger.shop.entity.OrderProduct;
import com.jason.mirageledger.shop.service.OrderProductService;
import com.jason.mirageledger.shop.service.OrderService;
import com.jason.mirageledger.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;


@RestController
@RequestMapping("/mirageLedger/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    @Value("${baseImagePath}")
    private String baseImagePath;

    // 创建订单
    @PostMapping("")
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(@RequestBody Order order) {
        RestPreconditions.checkParamArgument(order.getOrderPrice() != null && order.getOrderPrice() > 0, "订单总金额必须大于0", HttpStatus.BAD_REQUEST);
        RestPreconditions.checkParamArgument(order.getOrderProducts() != null && !order.getOrderProducts().isEmpty(), "订单商品不能为空", HttpStatus.BAD_REQUEST);
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(order.getAddressId()), "收货地址不能为空", HttpStatus.BAD_REQUEST);

        // 保存Order对象到数据库
        orderService.save(order);

        // 保存订单商品信息
        // 设置订单商品信息的订单ID
        order.getOrderProducts().forEach(orderProduct -> {
            orderProduct.setId(null);
            orderProduct.setOrderId(order.getId());
            orderProduct.setTotalPrice(orderProduct.getPrice() * orderProduct.getNum());
            orderProduct.setCreateTime(null);
            orderProduct.setUpdateTime(null);
        });
        orderProductService.saveBatch(order.getOrderProducts());
        return order;
    }

    // 更新订单状态
    @PutMapping("/{id}")
    public Order updateOrderStatus(@PathVariable String id, @RequestBody Order order) {
        // 验证订单是否存在
        RestPreconditions.checkParamArgument(
                orderService.lambdaQuery().eq(Order::getId, id).count() > 0,
                "订单不存在", HttpStatus.NOT_FOUND);

        // 更新订单状态
        order.setId(id);
        orderService.updateById(order);

        return orderService.getById(id);
    }

    // 删除订单
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        orderService.removeById(id);
    }

    // 获取订单详情
    @GetMapping("/{id}")
    public Order getOrderDetail(@PathVariable String id) {
        Order order = orderService.getById(id);
        RestPreconditions.checkParamArgument(order != null, "订单未找到", HttpStatus.NOT_FOUND);
        List<OrderProduct> orderProducts = orderProductService.lambdaQuery().eq(OrderProduct::getOrderId, id).list();
        // 为每个订单商品设置图片
        orderProducts.forEach(orderProduct -> {
            orderProduct.setImage(baseImagePath + orderProduct.getProductId() + ".jpg");
            orderProduct.setProductName(productService.getById(orderProduct.getProductId()).getName());
        });
        order.setOrderProducts(orderProducts);
        return order;
    }

    // 分页获取订单列表
    @GetMapping("")
    public Page<Order> getOrdersPage(
            @RequestParam(value = "page", defaultValue = "1") int currentPage,
            @RequestParam(value = "rows", defaultValue = "10") int size,
            @RequestParam(value = "status", required = false) String status) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Order::getUpdateTime);
        String userId = AuthenticationUtil.getAuthentication();
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq(Order::getUserId, userId);
        }
        if (StringUtils.isNotBlank(status)) {
            queryWrapper.eq(Order::getStatus, status);
        }
        Page<Order> orderPage = orderService.page(new Page<>(currentPage, size), queryWrapper);
        orderPage.getRecords().forEach(order -> {
            List<OrderProduct> orderProducts = orderProductService.lambdaQuery().eq(OrderProduct::getOrderId, order.getId()).list();
            orderProducts.forEach(orderProduct -> {
                orderProduct.setImage(baseImagePath + orderProduct.getProductId() + ".jpg");
                orderProduct.setProductName(productService.getById(orderProduct.getProductId()).getName());
            });
            order.setOrderProducts(orderProducts);
        });
        return orderPage;
    }

}

