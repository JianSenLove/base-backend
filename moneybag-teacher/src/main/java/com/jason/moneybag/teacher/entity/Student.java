package com.jason.moneybag.teacher.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 学生表
 * @TableName MONEYBAG_STUDENT
 */
@TableName(value ="MONEYBAG_STUDENT")
@Data
public class Student implements Serializable {
    /**
     * 学生ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 教师ID
     */
    @TableField(value = "TEACHER_ID")
    private String teacherId;

    /**
     * 学生姓名
     */
    @TableField(value = "NAME_")
    private String name;
    /**
     * 学生姓名
     */
    @TableField(value = "NICKNAME_")
    private String nickname;

    /**
     * 学生头像
     */
    @TableField(value = "AVATAR_")
    private String avatar;

    /**
     * 学生性别
     */
    @TableField(value = "GENDER")
    private String gender;

    /**
     * 学生生日
     */
    @TableField(value = "BIRTHDATE_")
    private Date birthdate;

    /**
     * 学生本人手机号
     */
    @TableField(value = "PHONE_")
    private String phone;

    /**
     * 家长手机号
     */
    @TableField(value = "PARENT_PHONE")
    private String parentPhone;

    /**
     * 家长微信号
     */
    @TableField(value = "PARENT_WECHAT")
    private String parentWechat;

    /**
     * 学生情况备注
     */
    @TableField(value = "NOTES")
    private String notes;

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