package com.jason.mirageledger.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.course.entity.po.Course;
import com.jason.mirageledger.course.service.CourseService;
import com.jason.mirageledger.course.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* 
* @description 针对表【mirageledger_course(课程表)】的数据库操作Service实现
* @createDate 2024-02-02 15:59:57
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




