package com.tg.shop.core.domain.auth.cache;

import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.domain.auth.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: tg
 * @Date: 2019/3/16 16:54
 */

public class SellerStore implements Serializable {

    private Seller seller;

    private Store store;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
