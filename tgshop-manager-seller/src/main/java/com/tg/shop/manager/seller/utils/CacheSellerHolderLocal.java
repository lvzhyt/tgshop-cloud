package com.tg.shop.manager.seller.utils;

import com.tg.shop.core.domain.auth.cache.SellerUser;

/**
 * @Author: tg
 * @Date: 2019/3/16 18:03
 */
public class CacheSellerHolderLocal {

    private static ThreadLocal<SellerUser> local = new ThreadLocal<>();

    public static void setSellerUser(SellerUser sellerUser){
        local.set(sellerUser);
    }

    public static SellerUser getSellerUser(){
        return local.get();
    }
}
