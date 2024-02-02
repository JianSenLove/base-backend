package com.jason.mirageledger.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtil {
    public static String getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(currentUserId), "用户信息为空");
        return currentUserId;
    }

    public static Boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(currentUserId), "用户信息为空");
        return currentUserId.equals("admin");
    }
}
