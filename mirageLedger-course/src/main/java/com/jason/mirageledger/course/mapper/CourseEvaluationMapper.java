package com.jason.mirageledger.course.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.course.entity.po.CourseEvaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description 针对表【mirageledger_course_evaluation(课程分析评分表)】的数据库操作Mapper
 * @createDate 2024-02-14 23:40:15
 * @Entity com.jason.mirageledger.course.entity.CourseEvaluation
 */
public interface CourseEvaluationMapper extends BaseMapper<CourseEvaluation> {

    public CourseEvaluation getCourseEvaluationByIdAndUserId(@Param("id") String id, @Param("userId") String userId);

    public Page<CourseEvaluation> getCourseEvaluationPage(Page page, @Param("courseName") String courseName, @Param("userId") String userId);
}




