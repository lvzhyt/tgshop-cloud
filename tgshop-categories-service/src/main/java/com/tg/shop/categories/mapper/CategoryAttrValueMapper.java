package com.tg.shop.categories.mapper;

import com.tg.shop.core.domain.categories.entity.CategoryAttrValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryAttrValueMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int deleteByPrimaryKey(String categoryAttrValId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int insert(CategoryAttrValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int insertSelective(CategoryAttrValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    CategoryAttrValue selectByPrimaryKey(String categoryAttrValId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int updateByPrimaryKeySelective(CategoryAttrValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int updateByPrimaryKey(CategoryAttrValue record);
}