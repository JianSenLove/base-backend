package com.jason.mirageledger.shop.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 * 用户购物车表
 * @TableName mirageledger_cart
 */
@TableName(value ="mirageledger_cart")
@Data
public class Cart implements Serializable {
    /**
     * ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户ID
     */
    @TableField(value = "USER_ID", fill = FieldFill.INSERT)
    private String userId;

    /**
     * 商品ID
     */
    @TableField(value = "PRODUCT_ID")
    private String productId;

    @TableField(exist = false)
    private String productName;

    @TableField(exist = false)
    private Double price;

    @TableField(exist = false)
    private String image;

    /**
     * 数量
     */
    @TableField(value = "QUANTITY_")
    private Integer num;

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