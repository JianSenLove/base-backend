package com.jason.mirageledger.shop.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 订单表
 * @TableName mirageledger_order
 */
@TableName(value ="mirageledger_order")
@Data
public class Order implements Serializable {
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
     * 订单总价
     */
    @TableField(value = "ORDER_PRICE")
    private Double orderPrice;

    /**
     * 订单状态
     */
    @TableField(value = "STATUS_")
    private String status;

    @TableField(exist = false)
    private List<OrderProduct> orderProducts;

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