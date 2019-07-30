package com.tg.shop.trade.service.impl;

import com.tg.shop.core.domain.auth.entity.Member;
import com.tg.shop.core.domain.trade.entity.*;
import com.tg.shop.core.domain.trade.vo.OrderDetailVo;
import com.tg.shop.core.domain.trade.vo.OrderInfo;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.CacheMemberHolderLocal;
import com.tg.shop.trade.mapper.*;
import com.tg.shop.trade.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 订单服务
 * @author Administrator
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IdGenerator idGenerator;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private TradeSkuInventoryMapper tradeSkuInventoryMapper;
    @Resource
    private TradeSkuInventoryLogMapper tradeSkuInventoryLogMapper;
    @Resource
    private OrderLogMapper orderLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveOrder( Order order, List<OrderItem> orderItemList) {
        Member member = CacheMemberHolderLocal.getMember();
        int count = orderMapper.insertSelective(order);
        for (OrderItem item :
                orderItemList) {
            orderItemMapper.insertSelective(item);
        }
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderLogId(idGenerator.nextStringId());
        orderLog.setOrderId(order.getOrderId());
        orderLog.setOrderSn(order.getOrderSn());
        orderLog.setOrderState(String.valueOf(order.getOrderState()));
        String remark = "提交订单";
        orderLog.setRemark(remark);
        orderLog.setOperator(member.getNickName());
        orderLog.setCreator(order.getCreator());
        orderLog.setCreateTime(new Date());
        orderLogMapper.insertSelective(orderLog);
        // 扣除积分
        if(order.getIntegralNum()!=null&&order.getIntegralNum()>0){
            //TODO 扣除积分
        }

        return count;
    }

    @Override
    public Order getByOrderId(String orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultObject confirmOrder(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        List<OrderItem> list = orderItemMapper.getOrderItemsByOrderId(orderId);
        for (OrderItem item :
                list) {
            int retryCount = 0;
            // 乐观锁更新 重试3次
            final int MAX_RETRY = 3;
            while(retryCount<MAX_RETRY){
                retryCount++;
                TradeSkuInventory skuInventory = tradeSkuInventoryMapper.selectByPrimaryKey(item.getSkuId());
                if(skuInventory.getLeftNum()<item.getGoodsNum()){
                    throw new RuntimeException("库存不足.skuNo:"+item.getSkuNo());
                }
                skuInventory.setLockNum(skuInventory.getLockNum()+item.getGoodsNum());
                skuInventory.setModifier("sys confirmOrder");
                skuInventory.setModifyTime(new Date());
                int count = tradeSkuInventoryMapper.updateByVersion(skuInventory);
                if(count>0){
                    TradeSkuInventoryLog skuInventoryLog = new TradeSkuInventoryLog();
                    BeanUtils.copyProperties(skuInventory,skuInventoryLog);
                    skuInventoryLog.setTbId(idGenerator.nextStringId());
                    tradeSkuInventoryLogMapper.insertSelective(skuInventoryLog);
                    break;
                }
            }
        }
        order.setOrderState(OrderInfo.ORDER_STATE_WAIT_PAY);
        order.setModifier("confirmOrder");
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKey(order);
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderLogId(idGenerator.nextStringId());
        orderLog.setOrderId(order.getOrderId());
        orderLog.setOrderSn(order.getOrderSn());
        orderLog.setOrderState(String.valueOf(order.getOrderState()));
        String remark = "确认订单，锁定库存";
        orderLog.setRemark(remark);
        orderLog.setOperator("sys confirmOrder");
        orderLog.setCreator(order.getCreator());
        orderLog.setCreateTime(new Date());
        orderLogMapper.insertSelective(orderLog);

        return new ResultObject(count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOrder(Order order,OrderLog orderLog) {
        int count = orderMapper.updateByPrimaryKeySelective(order);
        orderLogMapper.insertSelective(orderLog);
        return  count;
    }

}
