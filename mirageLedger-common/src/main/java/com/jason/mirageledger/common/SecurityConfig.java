package com.jason.mirageledger.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 禁用CSRF保护
                .authorizeRequests()
                .antMatchers("/mirageLedger/user/login").permitAll() // 允许所有用户访问登录接口
                .anyRequest().authenticated() // 其他请求都需要认证
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 使用无状态会话; 这里不创建会话

        http
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        // 确保自定义JWT过滤器在UsernamePasswordAuthenticationFilter之前执行
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
