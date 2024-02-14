package com.jason.mirageledger.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.course.entity.po.CourseComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* 
* @description 针对表【mirageledger_course_comment(课程评价表)】的数据库操作Service
* @createDate 2024-02-14 23:41:54
*/
public interface CourseCommentService extends IService<CourseComment> {
    public Page<CourseComment> getCourseCommentPage(Integer page, Integer rows, String courseName, String userId);

}
