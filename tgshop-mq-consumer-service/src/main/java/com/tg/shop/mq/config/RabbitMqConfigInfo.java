package com.tg.shop.mq.config;

public interface RabbitMqConfigInfo {

    int DEFAULT_MAX_DELIVERY_NUM = 3;

    String EXCHANGE_HELLO = "hello.exchange";
    String QUEUE_HELLO = "hello.queue";
    String KEY_HELLO = "hello.key";

    /**
     * 交易 exchange
     */
    String EXCHANGE_TRADE ="trade.shop.exchange";


    /**
     * 扣减库存队列
     */
    String QUEUE_TRADE_DEDUCT_STOCK ="deduct.stock.trade.shop.queue";

    /**
     * 增加锁定的库存
     */
    String QUEUE_TRADE_INCREASE_STOCK ="increase.stock.trade.shop.queue";


    /**
     * 更新库存 重新设置库存
     */
    String QUEUE_TRADE_UPDATE_STOCK ="update.stock.trade.shop.queue";


    /**
     * 扣减库存 binding_key
     */
    String KEY_TRADE_DEDUCT_STOCK ="deduct.stock.trade.shop.key";

    /**
     * 增加库存 binding_key
     */
    String KEY_TRADE_INCREASE_STOCK ="increase.stock.trade.shop.key";


    /**
     * 商品 exchange
     */
    String EXCHANGE_PRODUCT ="product.shop.exchange";


    /**
     * 商品ES队列
     */
    String QUEUE_PRODUCT_ES ="es.product.shop.queue";

    /**
     * 更新商品索引 binding_key
     */
    String KEY_PRODUCT_ES_INDEX ="es.index.product.shop.key";

    int MQ_TYPE_HELLO = 0;

    /**
     * 商品索引 ES
     */
    int MQ_TYPE_PRODUCT_ES_INDEX = 1;

    /**
     * 扣减库存
     */
    int MQ_TYPE_TRADE_DEDUCT_STOCK = 2;

    /**
     * 增加库存 恢复库存
     */
    int MQ_TYPE_TRADE_INCREASE_STOCK = 3;

    /**
     * 修改库存
     */
    int MQ_TYPE_TRADE_UPDATE_STOCK = 4;

}
