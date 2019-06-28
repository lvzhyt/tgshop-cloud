package com.tg.shop.core.utils;

import com.tg.shop.core.domain.auth.cache.SellerStore;

/**
 * @Author: tg
 * @Date: 2019/3/16 18:03
 */
public class CacheSellerStoreHolderLocal {

    private static ThreadLocal<SellerStore> local = new ThreadLocal<>();

    public static void setSellerStore(SellerStore sellerStore){
        local.set(sellerStore);
    }

    public static SellerStore getSellerStore(){
        return local.get();
    }
}
