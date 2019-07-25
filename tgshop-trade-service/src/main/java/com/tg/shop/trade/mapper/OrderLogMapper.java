package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.OrderLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderLogMapper {
    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int deleteByPrimaryKey(String orderLogId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insert(OrderLog record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insertSelective(OrderLog record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    OrderLog selectByPrimaryKey(String orderLogId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKeySelective(OrderLog record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKey(OrderLog record);
}