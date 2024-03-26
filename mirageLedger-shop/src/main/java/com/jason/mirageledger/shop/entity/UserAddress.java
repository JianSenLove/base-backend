package com.jason.mirageledger.shop.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户地址表
 * @TableName mirageledger_user_address
 */
@TableName(value ="mirageledger_user_address")
@Data
public class UserAddress implements Serializable {
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
     * 地址详情
     */
    @TableField(value = "ADDRESS_")
    private String address;

    /**
     * 城市
     */
    @TableField(value = "CITY_")
    private String city;

    /**
     * 州/省
     */
    @TableField(value = "STATE_")
    private String state;

    /**
     * 国家
     */
    @TableField(value = "COUNTRY_")
    private String country;

    /**
     * 邮政编码
     */
    @TableField(value = "ZIP_CODE")
    private String zipCode;

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