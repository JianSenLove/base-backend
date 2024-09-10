package com.jason.moneybag.classes.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 历史课程记录表
 * @TableName MONEYBAG_COURSE_HISTORY
 */
@TableName(value ="MONEYBAG_COURSE_HISTORY")
@Data
public class CourseHistory implements Serializable {
    /**
     * 历史课程记录ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 课程实例ID
     */
    @TableField(value = "COURSE_INSTANCE_ID")
    private String courseInstanceId;

    /**
     * 是否签到
     */
    @TableField(value = "ATTENDED_")
    private Integer attended;

    /**
     * 缺课原因
     */
    @TableField(value = "REASON")
    private String reason;

    /**
     * 实际上课开始时间
     */
    @TableField(value = "ACTUAL_START_TIME")
    private Date actualStartTime;

    /**
     * 实际上课结束时间
     */
    @TableField(value = "ACTUAL_END_TIME")
    private Date actualEndTime;

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