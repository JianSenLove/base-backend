package com.jason.moneybag.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.moneybag.AuthenticationUtil;
import com.jason.moneybag.DefaultImages;
import com.jason.moneybag.teacher.entity.Student;

/**
* @author 付建森
* @description 针对表【MONEYBAG_STUDENT(学生表)】的数据库操作Service
* @createDate 2024-09-05 17:57:13
*/
public interface StudentService extends IService<Student> {
    @Override
    default boolean save(Student entity) {
        entity.setAvatar(DefaultImages.DEAFULT_STUDENT_AVATAR.getBase64Image());
        entity.setTeacherId(AuthenticationUtil.getUserId());
        return IService.super.save(entity);
    }
}
