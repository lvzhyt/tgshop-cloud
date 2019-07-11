package com.tg.shop.mq.producer;

public interface GoodsEsProducer {

    /**
     * 发送商品索引消息
     * @param goodsId
     */
    boolean send(String goodsId);

}
