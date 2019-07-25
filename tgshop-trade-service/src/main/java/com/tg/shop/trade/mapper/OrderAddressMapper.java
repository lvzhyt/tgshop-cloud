package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.OrderAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderAddressMapper {
    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int deleteByPrimaryKey(String orderAddressId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insert(OrderAddress record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insertSelective(OrderAddress record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    OrderAddress selectByPrimaryKey(String orderAddressId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKeySelective(OrderAddress record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKey(OrderAddress record);
}