package com.jason.moneybag.teacher.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.moneybag.teacher.entity.SystemInfo;
import com.jason.moneybag.teacher.service.SystemInfoService;
import com.jason.moneybag.teacher.mapper.SystemInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 付建森
* @description 针对表【MONEYBAG_SYSTEM_INFO(系统信息表)】的数据库操作Service实现
* @createDate 2024-09-05 17:56:54
*/
@Service
public class SystemInfoServiceImpl extends ServiceImpl<SystemInfoMapper, SystemInfo>
    implements SystemInfoService{
    @Override
    public void saveKeyValueByTeacherId(String teacherId, String key, String value) {
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setTeacherId(teacherId);
        systemInfo.setKey(key);
        systemInfo.setValue(value);
        this.save(systemInfo);
    }
}




