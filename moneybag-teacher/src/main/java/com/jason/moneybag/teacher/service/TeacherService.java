package com.jason.moneybag.teacher.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.moneybag.AuthenticationUtil;
import com.jason.moneybag.DefaultImages;
import com.jason.moneybag.teacher.entity.Teacher;

/**
* @author 付建森
* @description 针对表【MONEYBAG_TEACHER(教师表)】的数据库操作Service
* @createDate 2024-09-03 17:04:44
*/
public interface TeacherService extends IService<Teacher> {

    @Override
    default boolean save(Teacher entity) {
        // 配置默认头像
        entity.setAvatar(DefaultImages.DEFAULT_AVATAR.getBase64Image());
        return IService.super.save(entity);
    }

    @Override
    default boolean updateById(Teacher entity) {
        if (StringUtils.isBlank(entity.getName())) entity.setName(null);
        if (StringUtils.isBlank(entity.getPassword())) entity.setPassword(null);
        entity.setUseraccount(null);
        entity.setId(AuthenticationUtil.getUserId());
        return IService.super.updateById(entity);
    }
}
