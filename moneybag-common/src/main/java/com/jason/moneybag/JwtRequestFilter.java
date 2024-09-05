package com.jason.moneybag;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        // 检查 Authorization 头是否存在并且以 "Bearer " 开头
        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
            try {
                // 提取 JWT token，跳过 "Bearer " 前缀
                String jwtToken = authorizationHeader.substring(BEARER_PREFIX.length());

                // 从 token 中获取用户 ID
                String userId = jwtTokenUtil.getUserIdFromToken(jwtToken);

                // 检查用户是否已认证，且 token 是否有效
                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtTokenUtil.validateToken(jwtToken)) {
                    // 创建认证对象并将其放入安全上下文
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JwtException e) {
                // 如果 token 无效，发送 401 错误
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用户凭证无效");
                return;
            } catch (Exception e) {
                // 捕获其他潜在异常并记录日志
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "登录出错");
                return;
            }
        }

        // 继续处理请求
        chain.doFilter(request, response);
    }
}



