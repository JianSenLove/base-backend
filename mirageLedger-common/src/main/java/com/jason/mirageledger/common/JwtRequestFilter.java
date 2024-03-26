package com.jason.mirageledger.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String requestURL = request.getRequestURI();

        // 跳过登录接口的JWT验证
        if ("/mirageLedger/user/login".equals(requestURL)) {
            chain.doFilter(request, response);
            return;
        }

        // 跳过注册接口的JWT验证
        if ("/mirageLedger/user/register".equals(requestURL)) {
            chain.doFilter(request, response);
            return;
        }

        // 跳过图片获取接口的验证
        if (requestURL.contains("/mirageLedger/image")) {
            chain.doFilter(request, response);
            return;
        }

        if (StringUtils.isNotBlank(requestTokenHeader)) {
            try {
                String jwtToken = requestTokenHeader.substring(7);
                String userId = jwtTokenUtil.getUserIdFromToken(jwtToken);

                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtTokenUtil.validateToken(jwtToken, userId)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}


