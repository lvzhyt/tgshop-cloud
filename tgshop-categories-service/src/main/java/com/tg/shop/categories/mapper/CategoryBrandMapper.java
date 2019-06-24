package com.tg.shop.categories.mapper;

import com.tg.shop.core.domain.categories.entity.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryBrandMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int deleteByPrimaryKey(String categoryBrandId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int insert(CategoryBrand record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int insertSelective(CategoryBrand record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    CategoryBrand selectByPrimaryKey(String categoryBrandId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int updateByPrimaryKeySelective(CategoryBrand record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int updateByPrimaryKey(CategoryBrand record);
}