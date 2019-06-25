package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsSkuInventory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSkuInventoryMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(GoodsSkuInventory record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(GoodsSkuInventory record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    GoodsSkuInventory selectByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsSkuInventory record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(GoodsSkuInventory record);


    /**
     * 根据 id version 更新
     * @param record
     * @return
     */
    int updateSkuInventoryByVersion(GoodsSkuInventory record);
}