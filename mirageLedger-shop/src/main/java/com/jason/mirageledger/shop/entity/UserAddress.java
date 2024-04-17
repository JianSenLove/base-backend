package com.jason.mirageledger.shop.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 收货地址表
 * @TableName mirageledger_user_address
 */
@TableName(value ="mirageledger_user_address")
@Data
public class UserAddress implements Serializable {
    /**
     * 
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 
     */
    @TableField(value = "USER_ID", fill = FieldFill.INSERT)
    private String userId;

    /**
     * 收货人姓名
     */
    @TableField(value = "NAME_")
    private String name;

    /**
     * 收货人手机号码
     */
    @TableField(value = "MOBILE_")
    private String mobile;

    /**
     * 收货地址
     */
    @TableField(value = "ADDRESS_")
    private String address;

    /**
     * 楼号、门牌号
     */
    @TableField(value = "AREA_")
    private String area;

    /**
     * 是否为默认地址（0：否，1：是）
     */
    @TableField(value = "FAULT_")
    private Integer fault;

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