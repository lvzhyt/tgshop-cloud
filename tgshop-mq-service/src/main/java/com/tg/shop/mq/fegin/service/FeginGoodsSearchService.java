package com.tg.shop.mq.fegin.service;

import org.springframework.stereotype.Service;

@Service
public interface FeginGoodsSearchService {

    void updateGoodsIndex(String goodsId);

}
