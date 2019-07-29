package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    /**
     *
     * @mbg.generated Mon Jul 29 12:57:45 CST 2019
     */
    int deleteByPrimaryKey(String orderId);

    /**
     *
     * @mbg.generated Mon Jul 29 12:57:45 CST 2019
     */
    int insert(Order record);

    /**
     *
     * @mbg.generated Mon Jul 29 12:57:45 CST 2019
     */
    int insertSelective(Order record);

    /**
     *
     * @mbg.generated Mon Jul 29 12:57:45 CST 2019
     */
    Order selectByPrimaryKey(String orderId);

    /**
     *
     * @mbg.generated Mon Jul 29 12:57:45 CST 2019
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     *
     * @mbg.generated Mon Jul 29 12:57:45 CST 2019
     */
    int updateByPrimaryKey(Order record);
}