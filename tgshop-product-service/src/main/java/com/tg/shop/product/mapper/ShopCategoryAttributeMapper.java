package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.ShopCategoryAttribute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopCategoryAttributeMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String shopCategoryAttributeId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(ShopCategoryAttribute record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(ShopCategoryAttribute record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    ShopCategoryAttribute selectByPrimaryKey(String shopCategoryAttributeId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(ShopCategoryAttribute record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(ShopCategoryAttribute record);
}