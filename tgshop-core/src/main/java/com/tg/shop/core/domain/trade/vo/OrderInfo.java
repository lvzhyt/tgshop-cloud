package com.tg.shop.core.domain.trade.vo;

public interface OrderInfo {

    /**
     * 已取消
     */
    int ORDER_STATE_CANCLED  = 0;

    /**
     * 待确认
     * 未锁定库存
     */
    int ORDER_STATE_WAIT_CONFIRM  = 1;

    /**
     * 待支付
     */
    int ORDER_STATE_WAIT_PAY  = 2;

    /**
     * 待发货
     */
    int ORDER_STATE_WAIT_SEND  = 3;

    /**
     * 待收货
     */
    int ORDER_STATE_WAIT_RECEIVE  = 4;

    /**
     * 已完成
     */
    int ORDER_STATE_COMPLETE  = 5;



}
