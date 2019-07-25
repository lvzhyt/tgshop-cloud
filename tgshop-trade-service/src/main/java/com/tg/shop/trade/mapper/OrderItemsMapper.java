package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.OrderItems;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemsMapper {
    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int deleteByPrimaryKey(String orderItemId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insert(OrderItems record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insertSelective(OrderItems record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    OrderItems selectByPrimaryKey(String orderItemId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKeySelective(OrderItems record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKey(OrderItems record);
}