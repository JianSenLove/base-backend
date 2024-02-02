package com.jason.mirageledger.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.course.entity.po.Course;
import com.jason.mirageledger.course.entity.po.CourseEvaluation;
import com.jason.mirageledger.course.service.CourseEvaluationService;
import com.jason.mirageledger.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mirageLedger/{courseId}/evaluation")
public class CourseEvaluationController {

    @Autowired
    private CourseEvaluationService courseEvaluationService;

    @Autowired
    private CourseService courseService;

    @PostMapping("")
    public CourseEvaluation createCourseEvaluation(@PathVariable("courseId") String courseId, @RequestBody CourseEvaluation courseEvaluation) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        Course course = courseService.getById(courseId);
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");

        courseEvaluation.setCourseId(courseId);
        courseEvaluationService.save(courseEvaluation);
        return courseEvaluation;
    }

    @PutMapping("/{id}")
    public CourseEvaluation updateCourseEvaluation(@PathVariable("courseId") String courseId, @PathVariable("id") String id, @RequestBody CourseEvaluation courseEvaluation) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        Course course = courseService.getById(courseId);
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");

        CourseEvaluation existCourseEvaluation = courseEvaluationService.getById(id);
        RestPreconditions.checkParamArgument(existCourseEvaluation != null, "课程评价不存在!");

        RestPreconditions.checkParamArgument(courseEvaluation.getScore() != null && courseEvaluation.getScore() >= 0 && courseEvaluation.getScore() <= 10, "课程评价分数不正确");
        courseEvaluation.setId(id);
        courseEvaluationService.updateById(courseEvaluation);
        return courseEvaluation;
    }

    @DeleteMapping("/{id}")
    public void deleteCourseEvaluation(@PathVariable String id) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");
        courseEvaluationService.removeById(id);
    }

    @GetMapping("/{id}")
    public CourseEvaluation getCourseEvaluation(@PathVariable String courseId,
                                                @PathVariable String id) {
        Course course = courseService.getById(courseId);
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");
        RestPreconditions.checkParamArgument(course.getUserId().equals(AuthenticationUtil.getAuthentication()), "无权限查看该课程下的评分!");

        CourseEvaluation courseEvaluation = courseEvaluationService.getById(id);
        RestPreconditions.checkParamArgument(courseEvaluation != null, "课程评价不存在!");
        RestPreconditions.checkParamArgument(courseEvaluation.getCourseId().equals(courseId), "课程评价和课程不匹配!");
        return courseEvaluation;
    }

    @GetMapping("")
    public Page<CourseEvaluation> getCoureseEvaluationPage(@PathVariable String courseId,
                                                           @RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer rows) {
        Course course = courseService.getById(courseId);
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");
        RestPreconditions.checkParamArgument(course.getUserId().equals(AuthenticationUtil.getAuthentication()), "无权限查看该课程下的评分!");

        Page<CourseEvaluation> courseEvaluationPage = new Page<>(page, rows);
        courseEvaluationService.page(courseEvaluationPage,
                new LambdaQueryWrapper<CourseEvaluation>()
                        .eq(CourseEvaluation::getCourseId, courseId)
                        .orderByDesc(CourseEvaluation::getCreateTime));
        return courseEvaluationPage;
    }
}
