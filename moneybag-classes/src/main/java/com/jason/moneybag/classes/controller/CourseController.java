package com.jason.moneybag.classes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.moneybag.AuthenticationUtil;
import com.jason.moneybag.RestPreconditions;
import com.jason.moneybag.classes.entity.Course;
import com.jason.moneybag.classes.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moneybag/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 创建课程
    @PostMapping("/create")
    @Transactional
    public Course createCourse(@RequestBody Course course) {
        // 参数校验
        RestPreconditions.checkParamArgument(course.getTotalHours() != null, "预计总课时不能为空!");
        RestPreconditions.checkParamArgument(course.getHoursPerClass() != null, "每堂课时不能为空!");

        // 插入课程信息
        courseService.save(course);

        return course;
    }

    // 更新课程信息
    @PutMapping("/update")
    @Transactional
    public Course updateCourse(@RequestBody Course course) {
        // 参数校验：课程ID必须存在
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getId()), "课程ID不能为空!");

        // 查询课程是否存在
        Course existingCourse = courseService.getById(course.getId());
        RestPreconditions.checkParamArgument(existingCourse != null, "课程不存在!");

        if (!AuthenticationUtil.isAdmin()) {
            RestPreconditions.checkParamArgument(existingCourse.getTeacherId().equals(AuthenticationUtil.getUserId()), "无权限修改该课程!");
        }

        // 更新课程信息
        courseService.updateById(course);

        // 返回更新后的课程信息
        return courseService.getById(course.getId());
    }

    // 删除课程
    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteCourse(@PathVariable("id") String id) {
        // 参数校验：课程ID必须存在
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(id), "课程ID不能为空!");

        // 检查课程是否存在
        Course course = courseService.getById(id);
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");

        if (!AuthenticationUtil.isAdmin()) {
            RestPreconditions.checkParamArgument(course.getTeacherId().equals(AuthenticationUtil.getUserId()), "无权限删除该课程!");
        }

        // 删除课程
        courseService.removeById(id);
    }

    // 获取单个课程信息
    @GetMapping("/get/{id}")
    public Course getCourseById(@PathVariable("id") String id) {
        // 参数校验：课程ID必须存在
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(id), "课程ID不能为空!");

        // 获取课程信息
        Course course = courseService.getById(id);
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");

        if (!AuthenticationUtil.isAdmin()) {
            RestPreconditions.checkParamArgument(course.getTeacherId().equals(AuthenticationUtil.getUserId()), "无权限查看该课程!");
        }

        return course;
    }

    // 分页查询课程
    @GetMapping("/page")
    public Page<Course> getCoursePage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer rows,
                                      @RequestParam(required = false) String courseName) {

        // 构建分页对象
        Page<Course> coursePage = new Page<>(page, rows);

        // 构建查询条件
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();

        // 按课程名称模糊查询
        if (StringUtils.isNotBlank(courseName)) {
            queryWrapper.like(Course::getName, courseName);
        }

        // 按教师ID查询
        if (!AuthenticationUtil.isAdmin()) {
            queryWrapper.eq(Course::getTeacherId, AuthenticationUtil.getUserId());
        }

        // 返回分页结果
        return courseService.page(coursePage, queryWrapper);
    }
}

