package com.tg.shop.core.domain.trade.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Cart implements Serializable {
    /**
     * 表id
     */
    private String cartId;

    /**
     * 会员
     */
    private String memberId;

    private static final long serialVersionUID = 1L;
}