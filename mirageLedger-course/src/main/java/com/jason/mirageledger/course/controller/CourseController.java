package com.jason.mirageledger.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.course.entity.dto.CourseDTO;
import com.jason.mirageledger.course.entity.po.Course;
import com.jason.mirageledger.course.service.CourseService;
import com.jason.mirageledger.user.entity.po.User;
import com.jason.mirageledger.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/mirageLedger/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @PostMapping("")
    public Course createCourse(@RequestBody Course course) {

        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getName()), "课程名称不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getYear()), "课程学年不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getTerm()), "课程所属学期不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(course.getUserId()), "课程教师不能为空!");
        RestPreconditions.checkParamArgument(course.getYear().matches("\\d{4}-\\d{4}"), "课程学年格式不正确!");
        RestPreconditions.checkParamArgument(course.getTerm().matches("1|2"), "课程学期格式不正确!");

        Course existCourse = courseService.getOne(
                new LambdaQueryWrapper<Course>()
                        .eq(Course::getName, course.getName())
                        .eq(Course::getYear, course.getYear())
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
        RestPreconditions.checkParamArgument(course.getYear().matches("\\d{4}-\\d{4}"), "课程学年格式不正确!");
        RestPreconditions.checkParamArgument(course.getTerm().matches("1|2"), "课程学期格式不正确!");

        Course existCourse = courseService.getOne(
                new LambdaQueryWrapper<Course>()
                        .eq(Course::getName, course.getName())
                        .eq(Course::getYear, course.getYear())
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
    public CourseDTO getCourse(@PathVariable("id") String id) {
        Course course = courseService.getById(id);
        RestPreconditions.checkParamArgument(course != null, "课程不存在!");
        RestPreconditions.checkParamArgument(AuthenticationUtil.getAuthentication().equals("admin") || course.getUserId().equals(AuthenticationUtil.getAuthentication()), "无权限查看该课程!");
        User user = userService.getById(course.getUserId());
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        courseDTO.setUserName(user.getName());
        courseDTO.setDepartment(user.getDepartment());
        courseDTO.setUserId(user.getId());
        return courseDTO;
    }

    @GetMapping("")
    public Page<CourseDTO> getCouresePage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer rows,
                                          @RequestParam(required = false) String name) {
        Page<Course> coursePage = new Page<>(page, rows);
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!AuthenticationUtil.isAdmin()) {
            courseLambdaQueryWrapper.eq(Course::getUserId, AuthenticationUtil.getAuthentication());
        }
        if (StringUtils.isNotBlank(name)) {
            courseLambdaQueryWrapper.like(Course::getName, name);
        }
        courseLambdaQueryWrapper.orderByDesc(Course::getUpdateTime);
        courseService.page(coursePage, courseLambdaQueryWrapper);

        // 获取所有userId
        List<String> userIds = coursePage.getRecords().stream()
                .map(Course::getUserId)
                .distinct()
                .collect(Collectors.toList());
        // 批量获取User对象，并转换为Map
        Map<String, User> userMap = userService.listByIds(userIds).stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        List<CourseDTO> courseDTOs = coursePage.getRecords().stream().map(course -> {
            User user = userMap.get(course.getUserId());
            CourseDTO courseDTO = new CourseDTO();
            BeanUtils.copyProperties(course, courseDTO);
            if (user != null) {
                courseDTO.setUserName(user.getName());
                courseDTO.setDepartment(user.getDepartment());
                courseDTO.setUserId(user.getId());
            }
            return courseDTO;
        }).collect(Collectors.toList());

        Page<CourseDTO> courseDTOPage = new Page<>(coursePage.getCurrent(), coursePage.getSize(), coursePage.getTotal());
        courseDTOPage.setRecords(courseDTOs);
        return courseDTOPage;
    }
}
