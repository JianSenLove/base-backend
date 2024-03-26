package com.jason.mirageledger.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.shop.entity.UserAddress;
import com.jason.mirageledger.shop.service.UserAddressService;
import com.jason.mirageledger.shop.mapper.UserAddressMapper;
import org.springframework.stereotype.Service;

/**
* @author 付建森
* @description 针对表【mirageledger_user_address(用户地址表)】的数据库操作Service实现
* @createDate 2024-03-26 19:43:23
*/
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress>
    implements UserAddressService{

}




