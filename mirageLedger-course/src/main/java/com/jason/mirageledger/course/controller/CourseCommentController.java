package com.jason.mirageledger.course.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.course.entity.po.CourseComment;
import com.jason.mirageledger.course.service.CourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mirageLedger/comment")
public class CourseCommentController {

    @Autowired
    private CourseCommentService courseCommentService;

    @GetMapping("/{id}")
    public CourseComment getCourseComment(@PathVariable("id") String id) {
        CourseComment courseComment = courseCommentService.getById(id);
        RestPreconditions.checkParamArgument(courseComment != null, "课程评论不存在", HttpStatus.BAD_REQUEST);
        return courseComment;
    }

    @PostMapping("")
    public CourseComment createCourseComment(@RequestBody CourseComment courseComment) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能操作", HttpStatus.BAD_REQUEST);
        RestPreconditions.checkParamArgument(courseComment.getCourseId() != null, "课程ID不能为空", HttpStatus.BAD_REQUEST);
        courseCommentService.save(courseComment);
        return courseComment;
    }

    @PutMapping("/{id}")
    public CourseComment updateCourseComment(@PathVariable("id") String id, @RequestBody CourseComment courseComment) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能操作", HttpStatus.BAD_REQUEST);
        CourseComment existingCourseComment = courseCommentService.getById(id);
        RestPreconditions.checkParamArgument(existingCourseComment != null, "课程评论不存在", HttpStatus.BAD_REQUEST);

        courseComment.setId(id);
        courseCommentService.updateById(courseComment);
        return courseComment;
    }

    @DeleteMapping("/{id}")
    public void deleteCourseComment(@PathVariable("id") String id) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能操作", HttpStatus.BAD_REQUEST);
        courseCommentService.removeById(id);
    }

    @GetMapping("")
    public Page<CourseComment> getCourseCommentPage(@RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer rows,
                                                    @RequestParam(required = false) String courseName) {
        return courseCommentService.getCourseCommentPage(page, rows, courseName, AuthenticationUtil.getAuthentication());
    }
}
