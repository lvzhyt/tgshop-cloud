package com.tg.shop.core.domain.trade.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart implements Serializable {
    /**
     * 表id
     */
    private String cartId;

    /**
     * 会员
     */
    private String memberId;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * sku_id
     */
    private String skuId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * spec_info
     */
    private String specInfo;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 删除状态
     */
    private Integer isDel;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 版本号
     */
    private Integer version;

    private static final long serialVersionUID = 1L;
}