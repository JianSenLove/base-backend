package com.jason.moneybag.classes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.moneybag.classes.entity.Course;
import com.jason.moneybag.classes.service.CourseService;
import com.jason.moneybag.classes.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author 付建森
* @description 针对表【MONEYBAG_COURSE(课程表)】的数据库操作Service实现
* @createDate 2024-09-10 14:44:42
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




