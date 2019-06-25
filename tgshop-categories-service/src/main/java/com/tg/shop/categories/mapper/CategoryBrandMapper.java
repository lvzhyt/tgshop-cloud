package com.tg.shop.categories.mapper;

import com.tg.shop.core.domain.categories.entity.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryBrandMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int deleteByPrimaryKey(String categoryBrandId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insert(CategoryBrand record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insertSelective(CategoryBrand record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    CategoryBrand selectByPrimaryKey(String categoryBrandId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKeySelective(CategoryBrand record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKey(CategoryBrand record);
}