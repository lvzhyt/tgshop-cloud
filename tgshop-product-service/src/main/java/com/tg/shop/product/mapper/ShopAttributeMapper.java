package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.ShopAttribute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopAttributeMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String shopAttributeId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(ShopAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(ShopAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    ShopAttribute selectByPrimaryKey(String shopAttributeId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(ShopAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(ShopAttribute record);
}