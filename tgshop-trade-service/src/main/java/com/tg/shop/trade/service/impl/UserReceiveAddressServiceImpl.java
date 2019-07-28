package com.tg.shop.trade.service.impl;

import com.tg.shop.core.domain.auth.entity.Member;
import com.tg.shop.core.domain.trade.entity.UserReceiveAddress;
import com.tg.shop.trade.mapper.UserReceiveAddressMapper;
import com.tg.shop.trade.service.UserReceiveAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserReceiveAddressServiceImpl implements UserReceiveAddressService {

    @Resource
    private UserReceiveAddressMapper userReceiveAddressMapper;

    @Override
    public UserReceiveAddress getAddressById(String addressId) {
        return userReceiveAddressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public int saveAddress(UserReceiveAddress userReceiveAddress) {
        return userReceiveAddressMapper.insertSelective(userReceiveAddress);
    }

    @Override
    public int updateAddress(UserReceiveAddress userReceiveAddress) {
        return userReceiveAddressMapper.updateByPrimaryKeySelective(userReceiveAddress);
    }

    @Override
    public int deleteAddress(String addressId) {
        UserReceiveAddress address = new UserReceiveAddress();
        address.setAddressId(addressId);
        address.setIsDel(1);
        int count = userReceiveAddressMapper.updateByPrimaryKeySelective(address);
        return count;
    }

    @Override
    public List<UserReceiveAddress> findAddressByCondition(UserReceiveAddress condition) {
        return userReceiveAddressMapper.findAddressByCondition(condition);
    }

    @Override
    public boolean setDefaultReceiveAddress(String addressId) {
        UserReceiveAddress address = userReceiveAddressMapper.selectByPrimaryKey(addressId);
        if(address==null){
            return false;
        }
        UserReceiveAddress condition = new UserReceiveAddress();
        condition.setMemberId(address.getMemberId());
        condition.setUseDefault(1);
        List<UserReceiveAddress> list = userReceiveAddressMapper.findAddressByCondition(condition);
        for (UserReceiveAddress item :
                list) {
            item.setUseDefault(0);
            userReceiveAddressMapper.updateByPrimaryKeySelective(item);
        }
        address.setUseDefault(1);
        int count = userReceiveAddressMapper.updateByPrimaryKeySelective(address);
        return count>0;
    }

    @Override
    public UserReceiveAddress getMemberDefaultAddress(String memberId) {
        UserReceiveAddress condition = new UserReceiveAddress();
        condition.setMemberId(memberId);
        condition.setUseDefault(1);
        List<UserReceiveAddress> list = userReceiveAddressMapper.findAddressByCondition(condition);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
