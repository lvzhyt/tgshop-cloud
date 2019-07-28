package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.FreightChargeTempTrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FreightChargeTempTradeMapper {
    /**
     *
     * @mbg.generated Sun Jul 28 16:26:05 CST 2019
     */
    int deleteByPrimaryKey(String templateId);

    /**
     *
     * @mbg.generated Sun Jul 28 16:26:05 CST 2019
     */
    int insert(FreightChargeTempTrade record);

    /**
     *
     * @mbg.generated Sun Jul 28 16:26:05 CST 2019
     */
    int insertSelective(FreightChargeTempTrade record);

    /**
     *
     * @mbg.generated Sun Jul 28 16:26:05 CST 2019
     */
    FreightChargeTempTrade selectByPrimaryKey(String templateId);

    /**
     *
     * @mbg.generated Sun Jul 28 16:26:05 CST 2019
     */
    int updateByPrimaryKeySelective(FreightChargeTempTrade record);

    /**
     *
     * @mbg.generated Sun Jul 28 16:26:05 CST 2019
     */
    int updateByPrimaryKey(FreightChargeTempTrade record);
}