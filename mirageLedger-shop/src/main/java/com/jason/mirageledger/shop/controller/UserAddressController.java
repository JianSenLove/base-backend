package com.jason.mirageledger.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.mirageledger.common.AuthenticationUtil;
import com.jason.mirageledger.common.RestPreconditions;
import com.jason.mirageledger.shop.entity.UserAddress;
import com.jason.mirageledger.shop.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/mirageLedger/v1/userAddress")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    // 添加用户地址
    @PostMapping("")
    public UserAddress addUserAddress(@RequestBody UserAddress userAddress) {
        RestPreconditions.checkParamArgument(userAddress.getAddress() != null, "地址不能为空");
        RestPreconditions.checkParamArgument(userAddress.getCity() != null, "城市不能为空");
        RestPreconditions.checkParamArgument(userAddress.getCountry() != null, "国家不能为空");
        RestPreconditions.checkParamArgument(userAddress.getState() != null, "省份不能为空");
        String userId = AuthenticationUtil.getAuthentication();
        userAddress.setUserId(userId);
        userAddressService.saveOrUpdate(userAddress);
        return userAddress;
    }

    // 更新用户地址
    @PutMapping("/{id}")
    public UserAddress updateUserAddress(@PathVariable String id, @RequestBody UserAddress userAddress) {
        UserAddress existingUserAddress = userAddressService.getById(id);
        if (existingUserAddress == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "用户地址未找到");
        }
        if (StringUtils.isBlank(userAddress.getAddress())) {
            userAddress.setAddress(null);
        }
        if (StringUtils.isBlank(userAddress.getCity())) {
            userAddress.setCity(null);
        }
        if (StringUtils.isBlank(userAddress.getCountry())) {
            userAddress.setCountry(null);
        }
        if (StringUtils.isBlank(userAddress.getState())) {
            userAddress.setState(null);
        }

        userAddressService.updateById(existingUserAddress);

        return existingUserAddress;
    }

    // 删除用户地址
    @DeleteMapping("/{id}")
    public void deleteUserAddress(@PathVariable String id) {
        userAddressService.removeById(id);
    }

    // 获取用户的用户地址列表
    @GetMapping("")
    public Page<UserAddress> getUserUserAddress(@RequestParam(value = "page", defaultValue = "1") int currentPage,
                                                @RequestParam(value = "rows", defaultValue = "10") int size) {
        String userId = AuthenticationUtil.getAuthentication();
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAddress::getUserId, userId);
        return userAddressService.page(new Page<>(currentPage, size), queryWrapper);
    }
}
