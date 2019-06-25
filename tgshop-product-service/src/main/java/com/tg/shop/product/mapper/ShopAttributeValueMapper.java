package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.ShopAttributeValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopAttributeValueMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String attrValueId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(ShopAttributeValue record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(ShopAttributeValue record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    ShopAttributeValue selectByPrimaryKey(String attrValueId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(ShopAttributeValue record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(ShopAttributeValue record);


    List<ShopAttributeValue> findListByCondition(ShopAttributeValue condition);
}