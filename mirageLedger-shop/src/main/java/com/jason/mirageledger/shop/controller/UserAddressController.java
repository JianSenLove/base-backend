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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        // 如果默认地址，则将之前的默认地址改为非默认地址
        if (userAddress.getFault() == 1) {
            LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserAddress::getUserId, AuthenticationUtil.getAuthentication());
            queryWrapper.eq(UserAddress::getFault, 1);
            List<UserAddress> userAddressList = userAddressService.list(queryWrapper);
            if (userAddressList != null && userAddressList.size() > 0) {
                UserAddress userAddress1 = userAddressList.get(0);
                userAddress1.setFault(0);
                userAddressService.updateById(userAddress1);
            }
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
    @GetMapping("/page")
    public Page<UserAddress> getUserUserAddress(@RequestParam(value = "page", defaultValue = "1") int currentPage,
                                                @RequestParam(value = "rows", defaultValue = "10") int size) {
        String userId = AuthenticationUtil.getAuthentication();
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAddress::getUserId, userId);
        return userAddressService.page(new Page<>(currentPage, size), queryWrapper);
    }

    @GetMapping("/{id}")
    public UserAddress getUserAddress(@PathVariable String id) {
        UserAddress userAddress = userAddressService.getById(id);
        RestPreconditions.checkParamArgument(userAddress.getUserId().equals(AuthenticationUtil.getAuthentication()), "用户地址未找到");
        return userAddress;
    }

    @GetMapping("/default")
    public UserAddress getDefaultUserAddress() {
        String userId = AuthenticationUtil.getAuthentication();
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAddress::getUserId, userId);
        queryWrapper.eq(UserAddress::getFault, 1);
        UserAddress deafaultUserAddress =  userAddressService.getOne(queryWrapper);
        if(deafaultUserAddress != null) {
            return deafaultUserAddress;
        } else {
            List<UserAddress> userAddrs =  userAddressService.list(new LambdaQueryWrapper<UserAddress>().orderByDesc(UserAddress::getUpdateTime));
            if(userAddrs != null && !userAddrs.isEmpty()) {
                return userAddrs.get(0);
            } else {
                return null;
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/default")
    public UserAddress setDefaultUserAddress(@RequestParam String id) {
        // 移除原有的默认地址
        String userId = AuthenticationUtil.getAuthentication();
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAddress::getUserId, userId);
        queryWrapper.eq(UserAddress::getFault, 1);
        UserAddress userAddress = userAddressService.getOne(queryWrapper);
        if (userAddress != null) {
            userAddress.setFault(0);
            userAddressService.updateById(userAddress);
        }
        UserAddress newUserAddress = userAddressService.getById(id);
        RestPreconditions.checkParamArgument(newUserAddress.getUserId().equals(AuthenticationUtil.getAuthentication()), "用户地址未找到");
        newUserAddress.setFault(1);
        userAddressService.updateById(newUserAddress);
        return newUserAddress;
    }

    @GetMapping("/list")
    public List<UserAddress> getUserAddressList() {
        String userId = AuthenticationUtil.getAuthentication();
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAddress::getUserId, userId);
        queryWrapper.orderByDesc(UserAddress::getUpdateTime);
        queryWrapper.orderByDesc(UserAddress::getFault);
        return userAddressService.list(queryWrapper);
    }

}
