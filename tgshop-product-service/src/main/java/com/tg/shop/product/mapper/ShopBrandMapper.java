package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.ShopBrand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopBrandMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String shopBrandId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(ShopBrand record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(ShopBrand record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    ShopBrand selectByPrimaryKey(String shopBrandId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(ShopBrand record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(ShopBrand record);
}