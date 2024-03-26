package com.jason.mirageledger.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.mirageledger.shop.entity.Category;
import com.jason.mirageledger.shop.service.CategoryService;
import com.jason.mirageledger.shop.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 付建森
* @description 针对表【mirageledger_category(商品类别表)】的数据库操作Service实现
* @createDate 2024-03-26 19:58:45
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




