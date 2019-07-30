package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.TradeSkuInventory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeSkuInventoryMapper {
    /**
     *
     * @mbg.generated Mon Jul 29 18:24:07 CST 2019
     */
    int deleteByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Mon Jul 29 18:24:07 CST 2019
     */
    int insert(TradeSkuInventory record);

    /**
     *
     * @mbg.generated Mon Jul 29 18:24:07 CST 2019
     */
    int insertSelective(TradeSkuInventory record);

    /**
     *
     * @mbg.generated Mon Jul 29 18:24:07 CST 2019
     */
    TradeSkuInventory selectByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Mon Jul 29 18:24:07 CST 2019
     */
    int updateByPrimaryKeySelective(TradeSkuInventory record);

    /**
     *
     * @mbg.generated Mon Jul 29 18:24:07 CST 2019
     */
    int updateByPrimaryKey(TradeSkuInventory record);

    int updateByVersion(TradeSkuInventory skuInventory);
}