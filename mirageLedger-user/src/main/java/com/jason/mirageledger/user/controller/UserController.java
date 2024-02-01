package com.jason.mirageledger.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jason.mirageledger.common.R;
import com.jason.mirageledger.user.entity.po.User;
import com.jason.mirageledger.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R userLogin(@RequestBody User user) {

        if (StringUtils.isBlank(user.getCode()) || StringUtils.isBlank(user.getPassword()))
            return R.error("用户名或密码为空!");

        //判断用户是否存在
        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getCode, user.getCode()));
        if (one != null) {
            if (one.getPassword().equals(user.getPassword())) {
                // 生成jwt返回
                String token = Jwts.builder()
                        .setSubject(user.getCode())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 设置过期时间，例如1小时
                        .compact();
                HashMap map = new HashMap<>();
                map.put("token",token);
                return R.success(map);
            } else {
                return R.error("账号或密码有误");
            }
        } else {
            return R.error("用户不存在");
        }
    }
}
