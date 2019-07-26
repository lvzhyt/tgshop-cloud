package com.tg.shop.trade.service;

import com.tg.shop.core.domain.trade.entity.Cart;

import java.util.List;

public interface CartService {
    /**
     * 保存
     * @param cart
     * @return
     */
    int saveCart(Cart cart);

    Cart getCartById(String cartId);

    /**
     * 查找用户 购物车
     * @param memberId
     * @return
     */
    List<Cart> findCartByMember(String memberId);

    /**
     * 用户购物车
     * @param memberId
     * @param skuId
     * @return
     */
    Cart findCartByMemberSkuId(String memberId,String skuId);

    int updateCart(Cart record);
}
