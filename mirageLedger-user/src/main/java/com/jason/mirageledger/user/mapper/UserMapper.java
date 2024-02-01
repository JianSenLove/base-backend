package com.jason.mirageledger.user.mapper;

import com.jason.mirageledger.user.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @description 针对表【mirageledger_user(用户信息表)】的数据库操作Mapper
* @createDate 2024-02-01 15:52:30
* @Entity com.jason.mirageledger.user.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




