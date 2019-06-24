package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.ShopCategoryAttributeValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopCategoryAttributeValueMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String shopCategoryValueId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(ShopCategoryAttributeValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(ShopCategoryAttributeValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    ShopCategoryAttributeValue selectByPrimaryKey(String shopCategoryValueId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(ShopCategoryAttributeValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(ShopCategoryAttributeValue record);
}