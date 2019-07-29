package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
    /**
     *
     * @mbg.generated Mon Jul 29 15:54:51 CST 2019
     */
    int deleteByPrimaryKey(String orderItemId);

    /**
     *
     * @mbg.generated Mon Jul 29 15:54:51 CST 2019
     */
    int insert(OrderItem record);

    /**
     *
     * @mbg.generated Mon Jul 29 15:54:51 CST 2019
     */
    int insertSelective(OrderItem record);

    /**
     *
     * @mbg.generated Mon Jul 29 15:54:51 CST 2019
     */
    OrderItem selectByPrimaryKey(String orderItemId);

    /**
     *
     * @mbg.generated Mon Jul 29 15:54:51 CST 2019
     */
    int updateByPrimaryKeySelective(OrderItem record);

    /**
     *
     * @mbg.generated Mon Jul 29 15:54:51 CST 2019
     */
    int updateByPrimaryKey(OrderItem record);
}