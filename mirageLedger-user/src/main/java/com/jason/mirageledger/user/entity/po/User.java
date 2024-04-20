package com.jason.mirageledger.user.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 用户信息表
 * @TableName mirageledger_user
 */
@TableName(value ="mirageledger_user")
@Data
public class User implements Serializable {
    /**
     * ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户登录账号
     */
    @TableField(value = "CODE_")
    private String code;

    /**
     * 账号密码
     */
    @TableField(value = "PASSWORD_")
    private String password;

    /**
     * 用户名称
     */
    @TableField(value = "NAME_")
    private String name;

    /**
     * 用户描述
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

    @TableField(exist = false)
    private Boolean state = true;

    @TableField(exist = false)
    private String image;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}