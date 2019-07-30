package com.tg.shop.trade.service.impl;

import com.tg.shop.core.domain.trade.entity.OrderItem;
import com.tg.shop.trade.mapper.OrderItemMapper;
import com.tg.shop.trade.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        List<OrderItem> list = orderItemMapper.getOrderItemsByOrderId(orderId);
        return list;
    }
}
