package com.jason.mirageledger.common;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义元数据对象处理器
 */
@Component
public class CustomMetaObjectHandler {
//    /**
//     * 插入操作自动填充
//     *
//     * @param metaObject
//     */
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//        this.strictInsertFill(metaObject, "createTime", Timestamp.class, currentTime); // 使用strictInsertFill确保类型安全
//        this.strictInsertFill(metaObject, "updateTime", Timestamp.class, currentTime);
//
////        Map<String, String> userInfo = getCurrentUserIdAndName();
////        String userId = userInfo.get("userId");
////        String userName = userInfo.get("userName");
////        if (userId != null && userName != null) {
////            this.strictInsertFill(metaObject, "creatorId", String.class, userId);
////            this.strictInsertFill(metaObject, "creatorName", String.class, userName);
////        }
//    }
//
//
//    /**
//     * 更新操作自动填充
//     *
//     * @param metaObject
//     */
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        metaObject.setValue("updateTime", new Timestamp(System.currentTimeMillis()));
//    }
//
//    /**
//     * 查询当前登录用户信息
//     *
//     * @return
//     */
//    private Map<String, String> getCurrentUserIdAndName() {
//        Map<String, String> userInfo = new HashMap();
////        Credential currCredential = CredentialStore.getCurrCredential();
////        RestPreconditions.checkParamArgument(currCredential != null, "登录用户凭证信息为空", HttpStatus.BAD_REQUEST);
////
////        userInfo.put("userId", (String) currCredential.getUserInfo().get("id"));
////        userInfo.put("userName", (String) currCredential.getUserInfo().get("name"));
//        return userInfo;
//    }
}
