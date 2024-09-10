package com.jason.moneybag.classes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.moneybag.classes.entity.CourseHistory;
import com.jason.moneybag.classes.service.CourseHistoryService;
import com.jason.moneybag.classes.mapper.CourseHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 付建森
* @description 针对表【MONEYBAG_COURSE_HISTORY(历史课程记录表)】的数据库操作Service实现
* @createDate 2024-09-10 14:44:42
*/
@Service
public class CourseHistoryServiceImpl extends ServiceImpl<CourseHistoryMapper, CourseHistory>
    implements CourseHistoryService{

}




