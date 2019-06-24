package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsSkuAttributeValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSkuAttributeValueMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String tId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(GoodsSkuAttributeValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(GoodsSkuAttributeValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    GoodsSkuAttributeValue selectByPrimaryKey(String tId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsSkuAttributeValue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(GoodsSkuAttributeValue record);
}