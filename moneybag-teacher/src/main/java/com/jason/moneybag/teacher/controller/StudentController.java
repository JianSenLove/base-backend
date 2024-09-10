package com.jason.moneybag.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.moneybag.AuthenticationUtil;
import com.jason.moneybag.RestPreconditions;
import com.jason.moneybag.teacher.entity.Student;
import com.jason.moneybag.teacher.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moneybag/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 添加学生
    @PostMapping("/create")
    @Transactional
    public Student registerStudent(@RequestBody Student student) {
        // 参数验证：姓名不能为空
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(student.getName()), "学生姓名不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(student.getGender()), "学生性别不能为空!");

        // 使用姓名和小名进行重复性校验
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getName, student.getName());

        // 如果小名不为空，也加入到校验条件中
        if (StringUtils.isNotBlank(student.getNickname())) {
            queryWrapper.eq(Student::getNickname, student.getNickname());
        } else {
            // 如果小名为空，则仅检查是否有相同名字且小名也为空的学生
            queryWrapper.isNull(Student::getNickname);
        }

        // 校验是否已存在相同姓名+小名的记录
        Student existingStudent = studentService.getOne(queryWrapper);
        RestPreconditions.checkParamArgument(existingStudent == null, "学生已存在!");

        // 插入学生信息
        studentService.save(student);
        return student;
    }

    // 更新学生信息
    @PutMapping("/update/{id}")
    @Transactional
    public Student updateStudent(@PathVariable("id") String id, @RequestBody Student student) {

        // 校验当前学生是否存在
        Student currentStudent = studentService.getById(id);
        RestPreconditions.checkParamArgument(currentStudent != null, "学生不存在!");

        // 检查更新后的姓名+小名是否与其他学生冲突
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getName, student.getName());

        if (StringUtils.isNotBlank(student.getNickname())) {
            queryWrapper.eq(Student::getNickname, student.getNickname());
        } else {
            queryWrapper.isNull(Student::getNickname);
        }

        // 确保跳过当前学生的记录（防止自己和自己冲突）
        queryWrapper.ne(Student::getId, id);

        // 检查是否存在姓名和小名重复的其他学生
        Student existingStudent = studentService.getOne(queryWrapper);
        RestPreconditions.checkParamArgument(existingStudent == null, "该姓名或小名的学生已存在，无法更新!");

        // 确保更新的是当前学生的记录
        student.setId(id);

        // 更新学生信息
        studentService.updateById(student);

        // 返回更新后的学生信息
        return studentService.getById(id);
    }


    // 删除学生
    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteStudent(@PathVariable("id") String id) {
        Student currentStudent = studentService.getById(id);
        RestPreconditions.checkParamArgument(currentStudent != null, "学生不存在!");
        if (!AuthenticationUtil.isAdmin()) {
            RestPreconditions.checkParamArgument(currentStudent.getTeacherId().equals(AuthenticationUtil.getUserId()), "只有该学生的老师才能删除该学生!");
        }

        // 删除学生记录
        studentService.removeById(id);
    }

    // 获取单个学生信息
    @GetMapping("/getOne/{id}")
    public Student getStudent(@PathVariable String id) {
        // 查询学生信息
        Student student = studentService.getById(id);
        RestPreconditions.checkParamArgument(student != null, "学生不存在!");

        if (!AuthenticationUtil.isAdmin()) {
            RestPreconditions.checkParamArgument(student.getTeacherId().equals(AuthenticationUtil.getUserId()), "只有该学生的老师才能查看该学生信息!");
        }

        return student;
    }

    // 分页查询学生信息
    @GetMapping("/page")
    public Page<Student> getStudentPage(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer rows,
                                        @RequestParam(required = false) String name) {
        // 构建分页查询条件
        Page<Student> studentPage = new Page<>(page, rows);
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 如果姓名不为空，按姓名进行模糊查询
        if (StringUtils.isNotBlank(name)) {
            studentLambdaQueryWrapper.like(Student::getName, name);
        }

        if (!AuthenticationUtil.isAdmin()) {
            studentLambdaQueryWrapper.eq(Student::getTeacherId, AuthenticationUtil.getUserId());
        }

        // 返回分页查询结果
        return studentService.page(studentPage, studentLambdaQueryWrapper);
    }
}

