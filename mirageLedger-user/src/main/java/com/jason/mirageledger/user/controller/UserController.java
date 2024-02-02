package com.jason.mirageledger.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.JwtTokenUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.user.entity.po.User;
import com.jason.mirageledger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mirageLedger/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public Map<String, String> userLogin(@RequestBody User user) {

        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(user.getCode()) || StringUtils.isNotBlank(user.getPassword()), "账号或密码为空!");

        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getCode, user.getCode()));

        RestPreconditions.checkParamArgument(one != null, "账号不存在");

        RestPreconditions.checkParamArgument(one.getPassword().equals(user.getPassword()), "密码不正确");

        final String token = jwtTokenUtil.generateToken(one.getId());
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @PostMapping("/register")
    public User userRegister(@RequestBody User user) {

        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(user.getCode()), "账号不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(user.getName()), "用户名不能为空!");
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(user.getPassword()), "密码不能为空!");

        User existUser = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getCode, user.getCode()));
        RestPreconditions.checkParamArgument(existUser == null, "账号已存在!");

        userService.save(user);
        return user;
    }
}
