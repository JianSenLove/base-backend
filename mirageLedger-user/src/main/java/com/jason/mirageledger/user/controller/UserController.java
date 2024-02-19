package com.jason.mirageledger.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.JwtTokenUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.user.entity.po.User;
import com.jason.mirageledger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        RestPreconditions.checkParamArgument(StringUtils.isNotBlank(user.getDepartment()), "所属院系不能为空!");

        User existUser = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getCode, user.getCode()));
        RestPreconditions.checkParamArgument(existUser == null, "账号已存在!");

        userService.save(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user) {

        RestPreconditions.checkParamArgument(id.equals(AuthenticationUtil.getAuthentication()) || AuthenticationUtil.isAdmin(), "只能修改自己账户的密码", HttpStatus.FORBIDDEN);

        User checkIdUser = userService.getById(id);
        RestPreconditions.checkParamArgument(checkIdUser != null, "用户不存在!");

        if (StringUtils.isBlank(user.getName())) user.setName(null);
        if (StringUtils.isBlank(user.getPassword())) user.setPassword(null);
        user.setId(id);
        user.setCode(null);
        userService.updateById(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");
        userService.removeById(id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String id) {
        RestPreconditions.checkParamArgument(id.equals(AuthenticationUtil.getAuthentication()) || AuthenticationUtil.isAdmin(), "只能查看自己的账户信息", HttpStatus.FORBIDDEN);

        User user = userService.getById(id);
        RestPreconditions.checkParamArgument(user != null, "用户不存在!");
        return user;
    }

    @GetMapping("")
    public Page<User> getUserPage(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer rows,
                                  @RequestParam(required = false) String name) {

        RestPreconditions.checkParamArgument(AuthenticationUtil.isAdmin(), "只有管理员能进行操作");

        Page<User> userPage = new Page<>(page, rows);
        LambdaQueryWrapper<User> UserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            UserLambdaQueryWrapper.like(User::getName, name);
        }

        UserLambdaQueryWrapper.orderByDesc(User::getUpdateTime);
        userService.page(userPage,UserLambdaQueryWrapper);
        return userPage;
    }

    //根据code获取用户接口
    @GetMapping("/code/{code}")
    public User getUserByCode(@PathVariable("code") String code) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getCode, code));
        RestPreconditions.checkParamArgument(user != null, "用户不存在!");
        RestPreconditions.checkParamArgument(user.getId().equals(AuthenticationUtil.getAuthentication()) || AuthenticationUtil.isAdmin(), "只能查看自己的账户信息", HttpStatus.FORBIDDEN);
        return user;
    }
}
