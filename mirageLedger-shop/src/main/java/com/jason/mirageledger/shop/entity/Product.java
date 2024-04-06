package com.jason.mirageledger.shop.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 商品信息表
 * @TableName mirageledger_product
 */
@TableName(value ="mirageledger_product")
@Data
public class Product implements Serializable {
    /**
     * ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 商品名称
     */
    @TableField(value = "NAME_")
    private String name;

    /**
     * 类别ID
     */
    @TableField(value = "CATEGORY_ID")
    private String categoryId;

    /**
     * 类别名称
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 商品价格
     */
    @TableField(value = "PRICE_")
    private Double price;

    /**
     * 商品库存
     */
    @TableField(value = "STOCK_")
    private Integer stock;

    /**
     * 用户ID
     */
    @TableField(value = "USER_ID", fill = FieldFill.INSERT)
    private String userId;

    /**
     * 商品描述
     */
    @TableField(value = "DESC_")
    private String desc;

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

    /**
     * 图片请求路径
     */
    @TableField(exist = false)
    private String image;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}