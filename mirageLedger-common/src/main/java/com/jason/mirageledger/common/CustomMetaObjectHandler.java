package com.jason.mirageledger.common;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义元数据对象处理器
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作自动填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        this.strictInsertFill(metaObject, "createTime", Timestamp.class, currentTime); // 使用strictInsertFill确保类型安全
        this.strictInsertFill(metaObject, "updateTime", Timestamp.class, currentTime);
    }


    /**
     * 更新操作自动填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", new Timestamp(System.currentTimeMillis()));
    }
}
