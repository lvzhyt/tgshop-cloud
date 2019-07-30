package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.TradeSkuInventoryLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeSkuInventoryLogMapper {
    /**
     *
     * @mbg.generated Mon Jul 29 22:10:47 CST 2019
     */
    int deleteByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Mon Jul 29 22:10:47 CST 2019
     */
    int insert(TradeSkuInventoryLog record);

    /**
     *
     * @mbg.generated Mon Jul 29 22:10:47 CST 2019
     */
    int insertSelective(TradeSkuInventoryLog record);

    /**
     *
     * @mbg.generated Mon Jul 29 22:10:47 CST 2019
     */
    TradeSkuInventoryLog selectByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Mon Jul 29 22:10:47 CST 2019
     */
    int updateByPrimaryKeySelective(TradeSkuInventoryLog record);

    /**
     *
     * @mbg.generated Mon Jul 29 22:10:47 CST 2019
     */
    int updateByPrimaryKey(TradeSkuInventoryLog record);
}