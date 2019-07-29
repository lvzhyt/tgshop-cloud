package com.tg.shop.trade.service.impl;

import com.tg.shop.core.domain.trade.entity.Order;
import com.tg.shop.core.domain.trade.entity.OrderItem;
import com.tg.shop.core.domain.trade.vo.OrderDetailVo;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.trade.mapper.OrderItemMapper;
import com.tg.shop.trade.mapper.OrderMapper;
import com.tg.shop.trade.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单服务
 * @author Administrator
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveOrder( Order order, List<OrderItem> orderItemList) {
        int count = orderMapper.insertSelective(order);
        for (OrderItem item :
                orderItemList) {
            orderItemMapper.insertSelective(item);
        }
        // 扣除积分
        if(order.getIntegralNum()!=null&&order.getIntegralNum()>0){
            //TODO 扣除积分
        }

        return count;
    }
}
