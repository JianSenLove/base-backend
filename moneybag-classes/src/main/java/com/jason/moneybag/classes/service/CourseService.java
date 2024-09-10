package com.jason.moneybag.classes.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jason.moneybag.AuthenticationUtil;
import com.jason.moneybag.classes.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.moneybag.classes.entity.enums.CourseTypeEnums;

/**
* @author 付建森
* @description 针对表【MONEYBAG_COURSE(课程表)】的数据库操作Service
* @createDate 2024-09-10 14:44:42
*/
public interface CourseService extends IService<Course> {
    @Override
    default boolean save(Course entity) {
        entity.setTeacherId(AuthenticationUtil.getUserId());
        if (StringUtils.isBlank(entity.getName())) {
            entity.setName(CourseTypeEnums.PIANO_LESSON.getDescription());
        }
        if (StringUtils.isBlank(entity.getCourseType())) {
            entity.setCourseType(CourseTypeEnums.ONE_TO_ONE.getDescription());
        }
        if (entity.getHoursPerClass() == null) {
            entity.setHoursPerClass(45);
        }
        if (entity.getTotalHours() == null) {
            entity.setTotalHours(10);
        }
        entity.setFinished(false);
        return IService.super.save(entity);
    }
}
