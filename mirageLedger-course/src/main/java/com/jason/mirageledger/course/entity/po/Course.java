package com.jason.mirageledger.course.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 课程表
 * @TableName mirageledger_course
 */
@TableName(value ="mirageledger_course")
@Data
public class Course implements Serializable {
    /**
     * ID
     */
    @TableId(value = "ID_", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 课程名称
     */
    @TableField(value = "NAME_")
    private String name;

    /**
     * 用户ID
     */
    @TableField(value = "USER_ID")
    private String userId;

    /**
     * 学年
     */
    @TableField(value = "YEAR_")
    private String year;

    /**
     * 学期
     */
    @TableField(value = "TERM_")
    private String term;

    /**
     * 课程描述
     */
    @TableField(value = "DESC_")
    private String desc;

    /**
     * 课程状态
     */
    @TableField(exist = false)
    private Boolean state = true;

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