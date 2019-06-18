package com.tg.shop.core.utils;

/**
 * @Author: tg
 * @Date: 2019/3/9 14:38
 */
public class RedisUtil {

    /**
     * 会员前缀
     */
    public static final String PRE_KEY_MEMBER = "member-";
    /**
     * admin管理前缀
     */
    public static final String PRE_KEY_ADMIN = "admin-";
    /**
     * 店铺卖家前缀
     */
    public static final String PRE_KEY_SELLER = "seller-";


    public static final String getSellerKey(String key){
        return PRE_KEY_SELLER+key;
    }

}
