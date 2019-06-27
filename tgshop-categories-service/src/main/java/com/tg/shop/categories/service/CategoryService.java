package com.tg.shop.categories.service;

import com.tg.shop.core.domain.categories.entity.Category;

import java.util.List;

/**
 * @Author: tg
 * @Date: 2018/12/18 15:31
 */
public interface CategoryService {

    int saveCategory(Category category);

    int deleteCategory(Category category);

    int updateCategory(Category category);

    Category getCategoryById(String categoryId);

    List<Category> findCategoryList(Category category);


}
