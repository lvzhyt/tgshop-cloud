package com.tg.shop.trade.service;

import com.tg.shop.core.domain.trade.entity.Order;
import com.tg.shop.core.domain.trade.entity.OrderItem;

import java.util.List;

public interface OrderService {

    /**
     * 保存订单 及订单商品
     * @return
     */
    int saveOrder(Order order, List<OrderItem> orderItemList);
}
