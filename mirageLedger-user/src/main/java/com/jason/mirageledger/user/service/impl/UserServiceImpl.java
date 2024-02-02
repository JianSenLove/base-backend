package com.jason.mirageledger.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.user.entity.po.User;
import com.jason.mirageledger.user.service.UserService;
import com.jason.mirageledger.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【mirageledger_user(用户信息表)】的数据库操作Service实现
* @createDate 2024-02-01 15:52:30
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




