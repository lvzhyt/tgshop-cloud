package com.tg.shop.core.utils;

/**
 * 本地线程 存储token
 * @Author: tg
 * @Date: 2019/3/16 17:59
 */
public class TokenHolderLocal {

    private static ThreadLocal<String> local = new ThreadLocal<String>();

    public static void setToken(String token){
        local.set(token);
    }

    public static String getToken(){
        return local.get();
    }
}
