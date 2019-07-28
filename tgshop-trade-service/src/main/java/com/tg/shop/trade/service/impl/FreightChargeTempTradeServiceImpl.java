package com.tg.shop.trade.service.impl;

import com.tg.shop.core.domain.trade.entity.FreightChargeTempTrade;
import com.tg.shop.trade.mapper.FreightChargeTempTradeMapper;
import com.tg.shop.trade.service.FreightChargeTempTradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FreightChargeTempTradeServiceImpl implements FreightChargeTempTradeService {

    @Resource
    private FreightChargeTempTradeMapper freightChargeTempTradeMapper;

    @Override
    public FreightChargeTempTrade getFreightChargeTemplateById(String templateId) {
        return freightChargeTempTradeMapper.selectByPrimaryKey(templateId);
    }
}
