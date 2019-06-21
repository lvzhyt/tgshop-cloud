package com.tg.shop.auth.service;

import com.tg.shop.core.domain.auth.entity.Store;

/**
 * @Author: tg
 * @Date: 2019/3/16 17:19
 */
public interface StoreService {

    /**
     * 创建店铺
     * @param store
     * @return
     */
    int saveStore(Store store);


    /**
     * @param storeId
     * @return
     */
    Store getStoreById(String storeId);

}
