package com.tg.shop.trade.service;

import com.tg.shop.core.domain.trade.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getOrderItemsByOrderId(String orderId);
}
