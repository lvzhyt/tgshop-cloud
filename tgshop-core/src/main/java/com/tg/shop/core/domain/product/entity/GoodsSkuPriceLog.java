package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsSkuPriceLog implements Serializable {
    /**
     * 表id
     */
    private String tbId;

    /**
     * 价格id
     */
    private String priceId;

    /**
     * 区域id
     */
    private String areaId;

    /**
     * 店铺
     */
    private String storeId;

    /**
     * 卖家id
     */
    private String sellerId;

    /**
     * 商品id
     */
    private String skuId;

    /**
     * 商品编号
     */
    private String skuNo;

    /**
     * 成本价格
     */
    private BigDecimal costPrice;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 开启plus价格 0 否 1是
     */
    private Integer plusPriceOpen;

    /**
     * plus价格
     */
    private BigDecimal plusPrice;

    /**
     * 开启超级会员价 0 否 1是
     */
    private Integer superVipPriceOpen;

    /**
     * 超级会员价
     */
    private BigDecimal superVipPrice;

    /**
     * 促销活动id
     */
    private String promoteId;

    /**
     * 促销活动名称
     */
    private String promoteName;

    /**
     * 备注
     */
    private String remark;

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