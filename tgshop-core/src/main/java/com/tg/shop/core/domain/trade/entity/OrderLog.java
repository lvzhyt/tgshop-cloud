package com.tg.shop.core.domain.trade.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderLog implements Serializable {
    /**
     * 日志id
     */
    private String orderLogId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 订单状态
     */
    private String orderState;

    /**
     * 描述
     */
    private String remark;

    /**
     * 操作人名称
     */
    private String operator;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}