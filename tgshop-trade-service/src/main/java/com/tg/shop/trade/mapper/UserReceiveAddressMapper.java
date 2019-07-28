package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.UserReceiveAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserReceiveAddressMapper {
    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int deleteByPrimaryKey(String addressId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insert(UserReceiveAddress record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insertSelective(UserReceiveAddress record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    UserReceiveAddress selectByPrimaryKey(String addressId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKeySelective(UserReceiveAddress record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKey(UserReceiveAddress record);

    /**
     * 条件查询
     * @param address
     * @return
     */
    List<UserReceiveAddress> findAddressByCondition(UserReceiveAddress address);
}