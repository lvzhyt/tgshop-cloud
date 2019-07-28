package com.tg.shop.trade.service;

import com.tg.shop.core.domain.trade.entity.UserReceiveAddress;

import java.util.List;

public interface UserReceiveAddressService {

    UserReceiveAddress getAddressById(String addressId);
    /**
     * 保存地址
     * @param userReceiveAddress
     * @return
     */
    int saveAddress(UserReceiveAddress userReceiveAddress);

    int updateAddress(UserReceiveAddress userReceiveAddress);

    int deleteAddress(String addressId);

    /**
     * 条件查找
     * @param condition
     * @return
     */
    List<UserReceiveAddress> findAddressByCondition(UserReceiveAddress condition);


    boolean setDefaultReceiveAddress(String addressId);

    /**
     * 获取默认地址
     * @param memberId
     * @return
     */
    UserReceiveAddress getMemberDefaultAddress(String memberId);
}
