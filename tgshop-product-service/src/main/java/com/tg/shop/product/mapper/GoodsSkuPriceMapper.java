package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsSkuPrice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSkuPriceMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(GoodsSkuPrice record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(GoodsSkuPrice record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    GoodsSkuPrice selectByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsSkuPrice record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(GoodsSkuPrice record);
}