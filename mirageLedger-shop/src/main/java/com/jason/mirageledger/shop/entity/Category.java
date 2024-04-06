package com.jason.mirageledger.shop.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 商品类别表
 * @TableName mirageledger_category
 */
@TableName(value ="mirageledger_category")
@Data
public class Category implements Serializable {
    /**
     * ID
     */
    @TableId(value = "ID_")
    private String id;

    /**
     * 类别名称
     */
    @TableField(value = "NAME_")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "DESC_")
    private String desc;

    @TableField(exist = false)
    private List<Product> products;

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