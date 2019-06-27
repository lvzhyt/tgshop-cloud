package com.tg.shop.categories.service.impl;

import com.github.pagehelper.PageInfo;
import com.tg.shop.categories.dao.BrandDao;
import com.tg.shop.categories.mapper.BrandMapper;
import com.tg.shop.categories.service.BrandService;
import com.tg.shop.core.domain.categories.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2018/12/19 17:16
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Resource
    private BrandMapper brandMapper;


    @Override
    public int saveBrand(Brand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Brand brand) {
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public PageInfo<Brand> findBrandPageList(Brand brand, int pageNum, int pageSize) {
        return brandDao.findBrandPageList(brand, pageNum,pageSize);
    }

    @Override
    public List<Brand> findBrandList(Brand brand) {
        return brandDao.findBrandList(brand);
    }
}
