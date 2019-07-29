package com.tg.shop.core.domain.trade.vo;

import com.tg.shop.core.domain.trade.entity.Order;
import com.tg.shop.core.domain.trade.entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailVo {

    private Order order;

    private List<OrderItem> orderItemList;
}
