package com.jason.moneybag.teacher.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 教师表
 * @TableName MONEYBAG_TEACHER
 */
@TableName(value ="MONEYBAG_TEACHER")
@Data
public class Teacher implements Serializable {
    /**
     * 教师ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 教师姓名
     */
    @TableField(value = "NAME_")
    private String name;

    /**
     * 教师头像
     */
    @TableField(value = "AVATAR_")
    private String avatar;

    /**
     * 教师账号
     */
    @TableField(value = "USERACCOUNT_")
    private String useraccount;

    /**
     * 教师密码
     */
    @TableField(value = "PASSWORD_")
    private String password;

    /**
     * 创建时间
     */
    @TableField(value = "CREATED_AT", fill = FieldFill.INSERT)
    private Timestamp createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATED_AT", fill = FieldFill.INSERT_UPDATE)
    private Timestamp updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}