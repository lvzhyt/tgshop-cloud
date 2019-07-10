package com.tg.shop.core.utils;

import com.tg.shop.core.domain.auth.entity.Seller;

/**
 * @Author: tg
 * @Date: 2019/3/16 18:03
 */
public class CacheSellerHolderLocal {

    private static ThreadLocal<Seller> local = new ThreadLocal<>();

    public static void setSeller(Seller seller){
        local.set(seller);
    }

    public static Seller getSeller(){
        return local.get();
    }

    public static void remove() {
        local.remove();
    }
}
