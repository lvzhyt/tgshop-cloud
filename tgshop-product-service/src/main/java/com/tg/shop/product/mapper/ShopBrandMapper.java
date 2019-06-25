package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.ShopBrand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopBrandMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String shopBrandId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(ShopBrand record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(ShopBrand record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    ShopBrand selectByPrimaryKey(String shopBrandId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(ShopBrand record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(ShopBrand record);
}