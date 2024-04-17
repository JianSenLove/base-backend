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
        RestPreconditions.checkParamArgument(userAddress.getName() != null, "联系人名称不能为空");
        RestPreconditions.checkParamArgument(userAddress.getMobile() != null, "联系人手机号不能为空");
        RestPreconditions.checkParamArgument(userAddress.getAddress() != null, "收货地址不能为空");
        RestPreconditions.checkParamArgument(userAddress.getArea() != null, "收货地址不能为空");
        if (userAddress.getFault()==null || userAddress.getFault() != 1) {
            userAddress.setFault(0);
        }
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
        if (StringUtils.isBlank(userAddress.getMobile())) {
            userAddress.setMobile(null);
        }
        if (StringUtils.isBlank(userAddress.getArea())) {
            userAddress.setArea(null);
        }
        if (StringUtils.isBlank(userAddress.getName())) {
            userAddress.setName(null);
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
