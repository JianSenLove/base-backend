package com.jason.moneybag.classes.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 教学计划表
 * @TableName MONEYBAG_TEACHING_PLAN
 */
@TableName(value ="MONEYBAG_TEACHING_PLAN")
@Data
public class TeachingPlan implements Serializable {
    /**
     * 教学计划ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 教师ID
     */
    @TableField(value = "TEACHER_ID")
    private String teacherId;

    /**
     * 学生ID
     */
    @TableField(value = "STUDENT_ID")
    private String studentId;

    /**
     * 计划名称
     */
    @TableField(value = "NAME_")
    private String name;

    /**
     * 计划内容
     */
    @TableField(value = "CONTENT")
    private String content;

    /**
     * 计划完成情况
     */
    @TableField(value = "COMPLETION_STATUS")
    private String completionStatus;

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