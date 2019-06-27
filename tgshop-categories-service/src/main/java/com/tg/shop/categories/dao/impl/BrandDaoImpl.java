package com.tg.shop.categories.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tg.shop.categories.dao.BrandDao;
import com.tg.shop.categories.mapper.BrandMapper;
import com.tg.shop.core.domain.categories.entity.Brand;
import com.tg.shop.core.generator.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2018/12/19 17:19
 */
@Repository
public class BrandDaoImpl implements BrandDao {

    @Resource
    private BrandMapper brandMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int saveBrand(Brand brand) {
        brand.setCreateTime(new Date());
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Brand brand) {
        brand.setModifyTime(new Date());
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public PageInfo<Brand> findBrandPageList(Brand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Brand> list = brandMapper.findBrandList(brand);
        PageInfo<Brand> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Brand> findBrandList(Brand brand) {
        return brandMapper.findBrandList(brand);
    }
}
