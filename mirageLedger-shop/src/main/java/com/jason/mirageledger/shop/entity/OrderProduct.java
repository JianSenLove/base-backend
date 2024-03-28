package com.jason.mirageledger.shop.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * 订单商品关联表
 * @TableName mirageledger_order_product
 */
@TableName(value ="mirageledger_order_product")
@Data
public class OrderProduct implements Serializable {
    /**
     * ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 订单ID
     */
    @TableField(value = "ORDER_ID")
    private String orderId;

    /**
     * 商品ID
     */
    @TableField(value = "PRODUCT_ID")
    private String productId;

    @TableField(exist = false)
    private String productName;

    /**
     * 数量
     */
    @TableField(value = "QUANTITY_")
    private Integer num;

    /**
     * 商品单价
     */
    @TableField(value = "PRICE_")
    private Double price;

    /**
     * 商品总价
     */
    @TableField(value = "TOTAL_PRICE")
    private Double totalPrice;

    @TableField(exist = false)
    private String image;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Timestamp createTime;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}