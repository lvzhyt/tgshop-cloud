package com.tg.shop.core.utils;

import com.tg.shop.core.domain.auth.entity.Store;

/**
 * @Author: tg
 * @Date: 2019/3/16 18:03
 */
public class CacheStoreHolderLocal {

    private static ThreadLocal<Store> local = new ThreadLocal<>();

    public static void setStore(Store store){
        local.set(store);
    }

    public static Store getStore(){
        return local.get();
    }
}
