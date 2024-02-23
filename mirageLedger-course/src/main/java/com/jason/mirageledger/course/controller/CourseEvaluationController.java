package com.jason.mirageledger.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.course.entity.dto.CourseEvaluationDTO;
import com.jason.mirageledger.course.entity.po.CourseEvaluation;
import com.jason.mirageledger.course.entity.po.Course;
import com.jason.mirageledger.course.service.CourseEvaluationService;
import com.jason.mirageledger.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mirageLedger/evaluation")
public class CourseEvaluationController {

    @Autowired
    private CourseEvaluationService courseEvaluationService;

    @Autowired
    private CourseService courseService;

    @PostMapping("")
    public CourseEvaluation createCourseEvaluation(@RequestBody CourseEvaluation courseEvaluation) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(courseEvaluation.getCourseId()), "课程ID不能为空", HttpStatus.BAD_REQUEST);
        Course course = courseService.getById(courseEvaluation.getCourseId());
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");

        //查询是否已有该课程ID的evaluation
        CourseEvaluation existCourseEvaluation = courseEvaluationService.getOne(new LambdaQueryWrapper<CourseEvaluation>().eq(CourseEvaluation::getCourseId, courseEvaluation.getCourseId()));
        RestPreconditions.checkParamArgument(existCourseEvaluation == null, "该课程ID已存在评价!", HttpStatus.BAD_REQUEST);

        courseEvaluationService.save(courseEvaluation);
        return courseEvaluation;
    }

    @PutMapping("/{id}")
    public CourseEvaluation updateCourseEvaluation(@PathVariable("id") String id, @RequestBody CourseEvaluation courseEvaluation) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(courseEvaluation.getCourseId()), "课程ID不能为空", HttpStatus.BAD_REQUEST);
        Course course = courseService.getById(courseEvaluation.getCourseId());
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");

        CourseEvaluation existCourseEvaluation = courseEvaluationService.getById(id);
        RestPreconditions.checkParamArgument(existCourseEvaluation != null, "课程评价不存在!");

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
    public CourseEvaluation getCourseEvaluation(@PathVariable String id) {
        CourseEvaluation courseEvaluation;
        if (AuthenticationUtil.isAdmin()) {
            courseEvaluation = courseEvaluationService.getCourseEvaluationByIdAndUserId(id, null);
        } else {
            courseEvaluation = courseEvaluationService.getCourseEvaluationByIdAndUserId(id, AuthenticationUtil.getAuthentication());
        }
        RestPreconditions.checkParamArgument(courseEvaluation != null, "课程评价不存在!");
        return courseEvaluation;
    }

    @GetMapping("")
    public Page<CourseEvaluationDTO> getCoureseEvaluationPage(@RequestParam(defaultValue = "1") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer rows,
                                                              @RequestParam(required = false) String courseName) {
        if (AuthenticationUtil.isAdmin()) {
            return courseEvaluationService.getCourseEvaluationPage(page, rows, courseName, null);
        }
        return courseEvaluationService.getCourseEvaluationPage(page, rows, courseName, AuthenticationUtil.getAuthentication());
    }

    @GetMapping("/exist/{courseId}")
    public Boolean isExistCourseEvaluation(@PathVariable String courseId) {
        CourseEvaluation existCourseEvaluation = courseEvaluationService.getOne(new LambdaQueryWrapper<CourseEvaluation>().eq(CourseEvaluation::getCourseId, courseId));
        return existCourseEvaluation != null;
    }
}
