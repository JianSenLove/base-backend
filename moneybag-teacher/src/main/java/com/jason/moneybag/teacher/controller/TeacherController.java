package com.jason.moneybag.teacher.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.moneybag.AuthenticationUtil;
import com.jason.moneybag.DefaultImageEnum;
import com.jason.moneybag.JwtTokenUtil;
import com.jason.moneybag.RestPreconditions;
import com.jason.moneybag.teacher.entity.Teacher;
import com.jason.moneybag.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moneybag/v1/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public String TeacherLogin(@RequestBody Teacher teacher) {

        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(teacher.getUseraccount()) || StringUtils.isNotBlank(teacher.getPassword()), "账号或密码不能为空!");

        Teacher one = teacherService.getOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getUseraccount, teacher.getUseraccount()));

        RestPreconditions.checkParamArgument(one != null, "账号不存在");

        RestPreconditions.checkParamArgument(one.getPassword().equals(teacher.getPassword()), "密码不正确");

        return jwtTokenUtil.generateToken(one.getId());
    }

    @PostMapping("/register")
    public Teacher TeacherRegister(@RequestBody Teacher teacher) {
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(teacher.getUseraccount()), "账号不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(teacher.getName()), "用户名不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(teacher.getPassword()), "密码不能为空!");

        Teacher existTeacher = teacherService.getOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getUseraccount, teacher.getUseraccount()));
        RestPreconditions.checkParamArgument(existTeacher == null, "账号已存在!");

        // 配置默认头像
        teacher.setAvatar(DefaultImageEnum.AVATAR.getimageBase64());

        // TODO 配置默认系统信息背景

        teacherService.save(teacher);
        return null;
    }

    @PutMapping("")
    public Teacher updateTeacher(@RequestBody Teacher teacher) {

        Teacher checkIdTeacher = teacherService.getById(AuthenticationUtil.getUserId());
        RestPreconditions.checkParamArgument(checkIdTeacher != null, "用户不存在!");

        if (StringUtils.isBlank(teacher.getName())) teacher.setName(null);
        if (StringUtils.isBlank(teacher.getPassword())) teacher.setPassword(null);
        teacher.setId(AuthenticationUtil.getUserId());
        teacherService.updateById(teacher);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") String id) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");
        teacherService.removeById(id);
    }

    @GetMapping("")
    public Teacher getTeacher() {
        Teacher Teacher = teacherService.getById(AuthenticationUtil.getUserId());
        RestPreconditions.checkParamArgument(Teacher != null, "用户不存在!");
        return null;
    }

    @GetMapping("/page")
    public Page<Teacher> getTeacherPage(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer rows,
                                  @RequestParam(required = false) String name) {

        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        Page<Teacher> TeacherPage = new Page<>(page, rows);
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            teacherLambdaQueryWrapper.like(Teacher::getName, name);
        }

        return teacherService.page(TeacherPage,teacherLambdaQueryWrapper);
    }
}
