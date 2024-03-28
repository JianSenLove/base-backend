package com.jason.mirageledger.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

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
    @TableField(value = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}