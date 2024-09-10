package com.jason.moneybag.classes.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 试课表
 * @TableName MONEYBAG_TRIAL_CLASS
 */
@TableName(value ="MONEYBAG_TRIAL_CLASS")
@Data
public class TrialClass implements Serializable {
    /**
     * 试课ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 教师ID
     */
    @TableField(value = "TEACHER_ID")
    private String teacherId;

    /**
     * 学生名称
     */
    @TableField(value = "STUDENT_NAME")
    private String studentName;

    /**
     * 试课是否成功
     */
    @TableField(value = "SUCCESS_")
    private Integer success;

    /**
     * 试课失败原因
     */
    @TableField(value = "REASON_")
    private String reason;

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