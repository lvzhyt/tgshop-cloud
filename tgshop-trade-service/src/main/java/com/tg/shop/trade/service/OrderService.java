package com.tg.shop.trade.service;

import com.tg.shop.core.domain.trade.entity.Order;
import com.tg.shop.core.domain.trade.entity.OrderItem;
import com.tg.shop.core.domain.trade.entity.OrderLog;
import com.tg.shop.core.entity.ResultObject;

import java.util.List;

public interface OrderService {

    /**
     * 保存订单 及订单商品
     * @return
     */
    int saveOrder(Order order, List<OrderItem> orderItemList);

    Order getByOrderId(String orderId);

    /**
     * 确认订单，锁定库存
     * @param orderId
     * @return
     */
    ResultObject confirmOrder(String orderId);

    int updateOrder(Order order, OrderLog orderLog);
}
