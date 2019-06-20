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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerUser implements Serializable {

    private Seller seller;

    private Store store;


}
