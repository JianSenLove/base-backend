package com.jason.mirageledger.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.shop.entity.Product;
import com.jason.mirageledger.shop.service.ProductService;
import com.jason.mirageledger.shop.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author 
* @description 针对表【mirageledger_product(商品信息表)】的数据库操作Service实现
* @createDate 2024-03-26 00:00:33
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




