package com.jason.mirageledger.course.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 课程分析评分表
 * @TableName mirageledger_course_evaluation
 */
@TableName(value ="mirageledger_course_evaluation")
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
     * 教学内容评价分
     */
    @TableField(value = "TEACHING_CONTENT")
    private Double teachingContent;

    /**
     * 教学方法评价分
     */
    @TableField(value = "TEACHING_METHOD")
    private Double teachingMethod;

    /**
     * 课程管理评价分
     */
    @TableField(value = "CURRICULUM_MANAGEMENT")
    private Double curriculumManagement;

    /**
     * 课程考核评价分
     */
    @TableField(value = "COURSE_ASSESSMENT")
    private Double courseAssessment;

    /**
     * 教学态度评价分
     */
    @TableField(value = "TEACHING_ATTITUDE")
    private Double teachingAttitude;

    /**
     * 学习收获评价分
     */
    @TableField(value = "LEARNING_HARVEST")
    private Double learningHarvest;

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