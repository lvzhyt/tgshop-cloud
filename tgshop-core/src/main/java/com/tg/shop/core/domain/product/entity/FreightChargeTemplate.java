package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class FreightChargeTemplate implements Serializable {
    /**
     * 运费id
     */
    private String templateId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 运费名称
     */
    private String templateName;

    /**
     * 类型 1 免邮 2 满额包邮 3 支付免邮 4 不包邮
     */
    private Integer templateType;

    /**
     * 满额
     */
    private BigDecimal fulfilPrice;

    /**
     * 运费
     */
    private BigDecimal freightPrice;

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