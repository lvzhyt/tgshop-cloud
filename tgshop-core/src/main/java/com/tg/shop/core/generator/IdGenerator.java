package com.tg.shop.core.generator;

/**
 * ID生成器
 * long型 snowflake redis 数据库生成
 * Created by tg on 2018/6/10.
 */
public interface IdGenerator {

    String nextStringId();

    /**
     * 16进制id
     * @return
     */
    String nextHexId();

    /**
     * 标准id
     * @return
     */
    long nextId();

    /**
     * 订单Id
     * @return
     */
    String nextOrderSn();

    /**
     * uuid
     * @return
     */
    String uuid();

}
