package com.jason.mirageledger.course.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.course.entity.po.CourseEvaluation;
import com.jason.mirageledger.course.service.CourseEvaluationService;
import com.jason.mirageledger.course.mapper.CourseEvaluationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 
* @description 针对表【mirageledger_course_evaluation(课程分析评分表)】的数据库操作Service实现
* @createDate 2024-02-14 23:40:15
*/
@Service
public class CourseEvaluationServiceImpl extends ServiceImpl<CourseEvaluationMapper, CourseEvaluation>
    implements CourseEvaluationService{

    @Autowired
    private CourseEvaluationMapper courseEvaluationMapper;

    @Override
    public CourseEvaluation getCourseEvaluationByIdAndUserId(String id, String userId) {
        return courseEvaluationMapper.getCourseEvaluationByIdAndUserId(id, userId);
    }

    @Override
    public Page<CourseEvaluation> getCourseEvaluationPage(Integer page, Integer rows, String courseName, String userId) {
        return courseEvaluationMapper.getCourseEvaluationPage(new Page(page, rows), courseName, userId);
    }
}




