package com.jason.mirageledger.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.course.entity.po.Course;
import com.jason.mirageledger.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mirageLedger/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("")
    public Course createCourse(@RequestBody Course course) {

        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getName()), "课程名称不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getTerm()), "课程所属学期不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getUserId()), "课程教师不能为空!");
        RestPreconditions.checkParamArgument(course.getTerm().matches("\\d{4}\\(上\\)|\\d{4}\\(下\\)"), "课程学期格式不正确!");

        Course existCourse = courseService.getOne(
                new LambdaQueryWrapper<Course>()
                        .eq(Course::getName, course.getName())
                        .eq(Course::getTerm, course.getTerm())
                        .eq(Course::getUserId, course.getUserId())
        );

        RestPreconditions.checkParamArgument(existCourse == null, "已存在相同的课程信息");

        courseService.save(course);
        return course;
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") String id, @RequestBody Course course) {

        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        Course checkIdCourse = courseService.getById(id);
        RestPreconditions.checkParamArgument(checkIdCourse != null, "课程不存在!");

        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getName()), "课程名称不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getTerm()), "课程所属学期不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getUserId()), "课程教师不能为空!");
        RestPreconditions.checkParamArgument(course.getTerm().matches("\\d{4}\\(上\\)|\\d{4}\\(下\\)"), "课程学期格式不正确!");

        Course existCourse = courseService.getOne(
                new LambdaQueryWrapper<Course>()
                        .eq(Course::getName, course.getName())
                        .eq(Course::getTerm, course.getTerm())
                        .eq(Course::getUserId, course.getUserId())
        );

        RestPreconditions.checkParamArgument(existCourse == null || existCourse.getId().equals(id), "已存在相同的课程信息");

        course.setId(id);
        courseService.updateById(course);
        return course;
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") String id) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");
        courseService.removeById(id);
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable("id") String id) {
        Course course = courseService.getById(id);
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");
        RestPreconditions.checkParamArgument(AuthenticationUtil.getAuthentication().equals("admin") || course.getUserId().equals(AuthenticationUtil.getAuthentication()), "无权限查看该课程!");
        return course;
    }

    @GetMapping("")
    public Page<Course> getCouresePage(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer rows) {
        Page<Course> coursePage = new Page<>(page, rows);
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!AuthenticationUtil.isAdmin()) {
            courseLambdaQueryWrapper.eq(Course::getUserId, AuthenticationUtil.getAuthentication());
        }
        courseLambdaQueryWrapper.orderByDesc(Course::getUpdateTime);
        courseService.page(coursePage,courseLambdaQueryWrapper);
        return coursePage;
    }
}
