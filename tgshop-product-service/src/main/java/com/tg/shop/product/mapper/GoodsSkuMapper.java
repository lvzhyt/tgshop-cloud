package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsSku;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSkuMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(GoodsSku record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(GoodsSku record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    GoodsSku selectByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsSku record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(GoodsSku record);
}