package com.jason.mirageledger.shop.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 * 用户商品浏览记录表
 * @TableName mirageledger_view_history
 */
@TableName(value ="mirageledger_view_history")
@Data
public class ViewHistory implements Serializable {
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

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Timestamp createTime;

    /**
     * 浏览次数
     */
    @TableField(value = "COUNT_")
    private Integer count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}