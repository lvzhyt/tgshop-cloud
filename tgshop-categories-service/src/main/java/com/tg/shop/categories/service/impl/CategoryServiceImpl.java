package com.tg.shop.categories.service.impl;

import com.tg.shop.categories.mapper.CategoryMapper;
import com.tg.shop.categories.service.CategoryService;
import com.tg.shop.core.domain.categories.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2018/12/18 20:04
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public int saveCategory(Category category) {
        return categoryMapper.insertSelective(category);
    }

    @Override
    public int deleteCategory(Category category) {
        Assert.notNull(category,"CategoryId is null");
        Assert.notNull(category.getCategoryId(),"CategoryId is null");
        category.setIsDel(1);
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int updateCategory(Category category) {
        Assert.notNull(category,"CategoryId is null");
        Assert.notNull(category.getCategoryId(),"CategoryId is null");
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public List<Category> findCategoryList(Category category) {
        return categoryMapper.findCategoryList(category);
    }

}
