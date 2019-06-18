package com.tg.shop.core.generator;

/**
 * ID生成器
 * long型 snowflake redis 数据库生成
 * Created by tg on 2018/6/10.
 */
public interface IdGenerator {
    String nextStringId();
    String nextHexId();
    long nextId();
}
