package com.jason.mirageledger.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.course.entity.po.CourseEvaluation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* 
* @description 针对表【mirageledger_course_evaluation(课程分析评分表)】的数据库操作Service
* @createDate 2024-02-14 23:40:15
*/
public interface CourseEvaluationService extends IService<CourseEvaluation> {

    public CourseEvaluation getCourseEvaluationByIdAndUserId(String id, String userId);

    public Page<CourseEvaluation> getCourseEvaluationPage(Integer page, Integer rows, String courseName, String userId);
}
