package com.tg.shop.core.domain.trade.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeSkuInventory implements Serializable {
    /**
     * 库存id skuId
     */
    private String skuId;

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
    private String goodsId;

    /**
     * 商品编号
     */
    private String skuNo;

    /**
     * 库存数量
     */
    private Integer inventoryNum;

    /**
     * 锁定数量
     */
    private Integer lockNum;

    /**
     * 可用数量
     */
    private Integer leftNum;

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