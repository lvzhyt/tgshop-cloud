package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.ShopCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopCategoryMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String shopCategoryId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(ShopCategory record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(ShopCategory record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    ShopCategory selectByPrimaryKey(String shopCategoryId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(ShopCategory record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(ShopCategory record);
}