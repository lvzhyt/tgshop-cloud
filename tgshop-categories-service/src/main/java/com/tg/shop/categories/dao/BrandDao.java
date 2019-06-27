package com.tg.shop.categories.dao;

import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.categories.entity.Brand;

import java.util.List;

/**
 * @Author: tg
 * @Date: 2018/12/19 17:19
 */
public interface BrandDao {

    int saveBrand(Brand brand);

    int updateBrand(Brand brand);

    PageInfo<Brand> findBrandPageList(Brand brand, int pageNum, int pageSize);

    List<Brand> findBrandList(Brand brand);
}
