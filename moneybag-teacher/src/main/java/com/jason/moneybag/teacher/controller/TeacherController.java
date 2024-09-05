package com.jason.moneybag.teacher.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.moneybag.JwtTokenUtil;
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
    public String TeacherLogin(@RequestBody Teacher Teacher) {

//        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(Teacher.getCode()) || StringUtils.isNotBlank(Teacher.getPassword()), "账号或密码为空!");
//
//        Teacher one = TeacherService.getOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getCode, Teacher.getCode()));

//        RestPreconditions.checkParamArgument(one != null, "账号不存在");
//
//        RestPreconditions.checkParamArgument(one.getPassword().equals(Teacher.getPassword()), "密码不正确");
//
//        final String token = jwtTokenUtil.generateToken(one.getId());
//        Map<String, Object> map = new HashMap<>();
//        map.put("token", token);
//        map.put("TeacherInfo", one);
//        return map;
        return "login";
    }

    @PostMapping("/register")
    public Teacher TeacherRegister(@RequestBody Teacher Teacher) {

//        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");
//
//        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(Teacher.getCode()), "账号不能为空!");
//        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(Teacher.getName()), "用户名不能为空!");
//        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(Teacher.getPassword()), "密码不能为空!");
//
//        Teacher existTeacher = TeacherService.getOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getCode, Teacher.getCode()));
//        RestPreconditions.checkParamArgument(existTeacher == null, "账号已存在!");
//
//        TeacherService.save(Teacher);
        return null;
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable("id") String id, @RequestBody Teacher Teacher) {

//        RestPreconditions.checkParamArgument(id.equals(AuthenticationUtil.getAuthentication()) || AuthenticationUtil.isAdmin(), "只能修改自己账户的密码", HttpStatus.FORBIDDEN);
//
//        Teacher checkIdTeacher = TeacherService.getById(id);
//        RestPreconditions.checkParamArgument(checkIdTeacher != null, "用户不存在!");
//
//        if (StringUtils.isBlank(Teacher.getName())) Teacher.setName(null);
//        if (StringUtils.isBlank(Teacher.getPassword())) Teacher.setPassword(null);
//        Teacher.setId(id);
//        TeacherService.updateById(Teacher);
//        Teacher.setImage(baseImagePath + Teacher.getId() + ".jpg");
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") String id) {
//        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");
//        TeacherService.removeById(id);
    }

    @GetMapping("/{id}")
    public String getTeacher(@PathVariable("id") String id) {
//        RestPreconditions.checkParamArgument(id.equals(AuthenticationUtil.getAuthentication()) || AuthenticationUtil.isAdmin(), "只能查看自己的账户信息", HttpStatus.FORBIDDEN);
//
//        Teacher Teacher = TeacherService.getById(id);
//        RestPreconditions.checkParamArgument(Teacher != null, "用户不存在!");
//        Teacher.setImage(baseImagePath + Teacher.getId() + ".jpg");
        return "666";
    }

    @GetMapping("")
    public Page<Teacher> getTeacherPage(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer rows,
                                  @RequestParam(required = false) String name) {
//
//        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");
//
//        Page<Teacher> TeacherPage = new Page<>(page, rows);
//        LambdaQueryWrapper<Teacher> TeacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if (StringUtils.isNotBlank(name)) {
//            TeacherLambdaQueryWrapper.like(Teacher::getName, name);
//        }
//
//        TeacherLambdaQueryWrapper.orderByDesc(Teacher::getUpdateTime);
//        TeacherService.page(TeacherPage,TeacherLambdaQueryWrapper);
//        TeacherPage.getRecords().forEach(Teacher -> Teacher.setImage(baseImagePath + Teacher.getId() + ".jpg"));
        return null;
    }

    //根据code获取用户接口
    @GetMapping("/code/{code}")
    public Teacher getTeacherByCode(@PathVariable("code") String code) {
//        Teacher Teacher = TeacherService.getOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getCode, code));
//        RestPreconditions.checkParamArgument(Teacher != null, "用户不存在!");
//        RestPreconditions.checkParamArgument(Teacher.getId().equals(AuthenticationUtil.getAuthentication()) || AuthenticationUtil.isAdmin(), "只能查看自己的账户信息", HttpStatus.FORBIDDEN);
        return null;
    }
}
