package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.ShopAttributeValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopAttributeValueMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String attrValueId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(ShopAttributeValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(ShopAttributeValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    ShopAttributeValue selectByPrimaryKey(String attrValueId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(ShopAttributeValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(ShopAttributeValue record);
}