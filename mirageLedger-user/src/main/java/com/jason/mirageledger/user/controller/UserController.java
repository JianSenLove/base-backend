package com.jason.mirageledger.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jason.mirageledger.common.JwtTokenUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.user.entity.po.User;
import com.jason.mirageledger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public Map<String,String> userLogin(@RequestBody User user) {

        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(user.getCode()) || StringUtils.isNotBlank(user.getPassword()), "用户名或密码为空!");

        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getCode, user.getCode()));

        RestPreconditions.checkParamArgument(one !=null, "用户不存在");

        RestPreconditions.checkParamArgument(one.getPassword().equals(user.getPassword()), "密码不正确");

        final String token = jwtTokenUtil.generateToken(one.getId());
        Map<String,String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
