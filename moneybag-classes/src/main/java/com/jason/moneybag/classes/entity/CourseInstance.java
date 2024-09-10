package com.jason.moneybag.classes.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 课程实例表
 * @TableName MONEYBAG_COURSE_INSTANCE
 */
@TableName(value ="MONEYBAG_COURSE_INSTANCE")
@Data
public class CourseInstance implements Serializable {
    /**
     * 课程实例ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 学生ID
     */
    @TableField(value = "STUDENT_ID")
    private String studentId;

    /**
     * 课程ID
     */
    @TableField(value = "COURSE_ID")
    private String courseId;

    /**
     * 课时费
     */
    @TableField(value = "FEE_")
    private Integer fee;

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