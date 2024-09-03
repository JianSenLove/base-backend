package com.jason.moneybag;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

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
        this.strictInsertFill(metaObject, "createdAt", Timestamp.class, currentTime); // 使用strictInsertFill确保类型安全
        this.strictInsertFill(metaObject, "updatedAt", Timestamp.class, currentTime);
//        String userId = AuthenticationUtil.getAuthentication();
//        this.strictInsertFill(metaObject, "userId", String.class, userId);
    }


    /**
     * 更新操作自动填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updatedAt", new Timestamp(System.currentTimeMillis()));
    }
}
