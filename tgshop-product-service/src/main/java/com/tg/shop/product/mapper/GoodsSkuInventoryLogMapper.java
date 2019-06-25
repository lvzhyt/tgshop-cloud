package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsSkuInventoryLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSkuInventoryLogMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(GoodsSkuInventoryLog record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(GoodsSkuInventoryLog record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    GoodsSkuInventoryLog selectByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsSkuInventoryLog record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(GoodsSkuInventoryLog record);
}