package com.jason.moneybag.classes.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 课程表
 * @TableName MONEYBAG_COURSE
 */
@TableName(value ="MONEYBAG_COURSE")
@Data
public class Course implements Serializable {
    /**
     * 课程ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 教师ID
     */
    @TableField(value = "TEACHER_ID")
    private String teacherId;

    /**
     * 课程名称
     */
    @TableField(value = "NAME_")
    private String name;

    /**
     * 预计总课时
     */
    @TableField(value = "TOTAL_HOURS_")
    private Integer totalHours;

    /**
     * 每堂课课时
     */
    @TableField(value = "HOURS_PER_CLASS")
    private Integer hoursPerClass;

    /**
     * 是否结课
     */
    @TableField(value = "FINSHED_")
    private Boolean finished;

    /**
     * 课程类型(一对一,一对多)
     */
    @TableField(value = "COURSE_TYPE")
    private String courseType;

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