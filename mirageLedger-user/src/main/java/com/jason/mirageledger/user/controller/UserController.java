package com.jason.mirageledger.user.controller;

import com.jason.mirageledger.common.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

//    @PostMapping("/login")
//    public R userLogin(@RequestBody Userinfo user) {
//
//        if(user.getUserAccount() == null || user.getUserPassword() == null)
//            return R.error("用户名或密码为空!");
//
//        //判断用户是否存在
//        Userinfo one = userinfoService.getOne(new LambdaQueryWrapper<Userinfo>().eq(Userinfo::getUserAccount, user.getUserAccount()));
//        if (one != null) {
//            if(one.getUserPassword().equals(user.getUserPassword())){
//                return R.success(one);
//            }
//            else{
//                return R.error("账号或密码错误");
//            }
//        } else {
//            return R.error("用户名不存在");
//        }
//    }
}
