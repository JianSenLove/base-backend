package com.jason.moneybag.classes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.moneybag.classes.entity.CourseInstance;
import com.jason.moneybag.classes.service.CourseInstanceService;
import com.jason.moneybag.classes.mapper.CourseInstanceMapper;
import org.springframework.stereotype.Service;

/**
* @author 付建森
* @description 针对表【MONEYBAG_COURSE_INSTANCE(课程实例表)】的数据库操作Service实现
* @createDate 2024-09-10 14:44:42
*/
@Service
public class CourseInstanceServiceImpl extends ServiceImpl<CourseInstanceMapper, CourseInstance>
    implements CourseInstanceService{

}




