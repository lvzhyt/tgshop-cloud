package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     *
     * @mbg.generated Wed Jul 31 12:48:56 CST 2019
     */
    int deleteByPrimaryKey(String orderId);

    /**
     *
     * @mbg.generated Wed Jul 31 12:48:56 CST 2019
     */
    int insert(Order record);

    /**
     *
     * @mbg.generated Wed Jul 31 12:48:56 CST 2019
     */
    int insertSelective(Order record);

    /**
     *
     * @mbg.generated Wed Jul 31 12:48:56 CST 2019
     */
    Order selectByPrimaryKey(String orderId);

    /**
     *
     * @mbg.generated Wed Jul 31 12:48:56 CST 2019
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     *
     * @mbg.generated Wed Jul 31 12:48:56 CST 2019
     */
    int updateByPrimaryKey(Order record);

    /**
     * 条件查找
     * @param condition
     * @return
     */
    List<Order> findOrderByCondition(Order condition);
}