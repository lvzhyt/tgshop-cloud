package com.tg.shop.auth.service.impl;

import com.tg.shop.auth.mapper.SellerMapper;
import com.tg.shop.auth.mapper.StoreMapper;
import com.tg.shop.auth.service.StoreService;
import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.domain.auth.entity.Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: tg
 * @Date: 2019/3/16 17:19
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private SellerMapper sellerMapper;

    @Override
    @Transactional
    public int saveStore(Store store) {
        int result = storeMapper.insertSelective(store);
        Seller seller = new Seller();
        seller.setSellerId(store.getSellerId());
        seller.setStoreId(store.getStoreId());
        sellerMapper.updateByPrimaryKeySelective(seller);

        return result;
    }

    @Override
    public Store getStoreById(String storeId) {
        return storeMapper.selectByPrimaryKey(storeId);
    }

}
