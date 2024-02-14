package com.jason.mirageledger.course.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.course.entity.po.CourseComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* 
* @description 针对表【mirageledger_course_comment(课程评价表)】的数据库操作Mapper
* @createDate 2024-02-14 23:41:54
* @Entity com.jason.mirageledger.course.entity.CourseComment
*/
public interface CourseCommentMapper extends BaseMapper<CourseComment> {
    public Page<CourseComment> getCourseCommentPage(Page page, @Param("courseName") String courseName, @Param("userId") String userId);
}




