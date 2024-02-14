package com.jason.mirageledger.course.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.course.entity.po.CourseComment;
import com.jason.mirageledger.course.service.CourseCommentService;
import com.jason.mirageledger.course.mapper.CourseCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 
* @description 针对表【mirageledger_course_comment(课程评价表)】的数据库操作Service实现
* @createDate 2024-02-14 23:41:54
*/
@Service
public class CourseCommentServiceImpl extends ServiceImpl<CourseCommentMapper, CourseComment>
    implements CourseCommentService{

    @Autowired
    private CourseCommentMapper courseCommentMapper;
    @Override
    public Page<CourseComment> getCourseCommentPage(Integer page, Integer rows, String courseId, String userId) {
        return courseCommentMapper.getCourseCommentPage(new Page(page, rows), courseId, userId);
    }
}




