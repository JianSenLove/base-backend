package com.jason.moneybag.teacher.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.moneybag.teacher.entity.Student;
import com.jason.moneybag.teacher.service.StudentService;
import com.jason.moneybag.teacher.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author 付建森
* @description 针对表【MONEYBAG_STUDENT(学生表)】的数据库操作Service实现
* @createDate 2024-09-05 17:57:13
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




