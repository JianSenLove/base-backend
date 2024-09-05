package com.jason.moneybag.teacher.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统信息表
 * @TableName MONEYBAG_SYSTEM_INFO
 */
@TableName(value ="MONEYBAG_SYSTEM_INFO")
@Data
public class SystemInfo implements Serializable {
    /**
     * 系统信息ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 教师ID
     */
    @TableField(value = "TEACHER_ID")
    private String teacherId;

    /**
     * 系统信息键
     */
    @TableField(value = "KEY_")
    private String key;

    /**
     * 系统信息值
     */
    @TableField(value = "VALUE_")
    private String value;

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