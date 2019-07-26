package com.tg.shop.trade.service.impl;

import com.tg.shop.core.domain.trade.entity.Cart;
import com.tg.shop.trade.mapper.CartMapper;
import com.tg.shop.trade.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;


    @Override
    public int saveCart(Cart cart) {
        return cartMapper.insertSelective(cart);
    }

    @Override
    public Cart getCartById(String cartId) {
        return cartMapper.selectByPrimaryKey(cartId);
    }

    @Override
    public List<Cart> findCartByMember(String memberId) {
        return cartMapper.findCartByMember(memberId);
    }


    @Override
    public Cart findCartByMemberSkuId(String memberId, String skuId) {
        return cartMapper.findCartByMemberSkuId(memberId,skuId);
    }

    @Override
    public int updateCart(Cart record) {
        return cartMapper.updateByPrimaryKeySelective(record);
    }
}
