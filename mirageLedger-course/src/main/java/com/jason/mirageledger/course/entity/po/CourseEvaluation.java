package com.jason.mirageledger.course.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * 课程评分表
 *
 * @TableName mirageledger_course_evaluation
 */
@TableName(value = "mirageledger_course_evaluation")
@Data
public class CourseEvaluation implements Serializable {
    /**
     * ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 课程ID
     */
    @TableField(value = "COURSE_ID")
    private String courseId;

    /**
     * 评价类型
     */
    @TableField(value = "TYPE_")
    private String type;

    /**
     * 课程评价分
     */
    @TableField(value = "SCORE_")
    private Double score;

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