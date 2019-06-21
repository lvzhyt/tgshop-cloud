package com.tg.shop.auth.dao;

import com.tg.shop.core.domain.auth.entity.Seller;

import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/9 17:04
 */
public interface SellerDao {

    /**
     * 根据登录名/手机查找用户
     * @param loginName
     * @return
     */
    Seller findSellerByLoginName(String loginName);

    List<Seller> findListByRecord(Seller record);
}
