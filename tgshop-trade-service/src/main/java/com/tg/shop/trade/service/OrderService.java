package com.tg.shop.trade.service;

import com.tg.shop.core.domain.trade.entity.Order;
import com.tg.shop.core.domain.trade.entity.OrderItem;
import com.tg.shop.core.domain.trade.entity.OrderLog;
import com.tg.shop.core.domain.util.PageResult;
import com.tg.shop.core.entity.ResultObject;

import java.util.List;

public interface OrderService {

    /**
     * 保存订单 及订单商品
     * @return
     */
    int saveOrder(Order order, List<OrderItem> orderItemList,OrderLog orderLog);

    Order getByOrderId(String orderId);

    /**
     * 确认订单，锁定库存
     * @param orderId
     * @return
     */
    ResultObject confirmOrder(String orderId);

    int updateOrder(Order order, OrderLog orderLog);

    /**
     * 取消订单
     * @return
     */
    ResultObject cancelOrder(Order order,OrderLog orderLog);

    /**
     * 取消订单 恢复库存
     * @param orderId
     * @return
     */
    ResultObject cancelOrderStock(String orderId);

    /**
     * 分页查找
     * @param condition
     * @param pageSize
     * @param pageNum
     */
    PageResult<Order> findOrderPageList(Order condition, Integer pageNum, Integer pageSize);

    /**
     * 拆单
     * @param orderId
     * @return
     */
    ResultObject disassembleOrder(String orderId);
}
