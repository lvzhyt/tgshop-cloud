package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsSkuAttribute implements Serializable {
    /**
     * 表id
     */
    private String tId;

    /**
     * 属性ID
     */
    private String attrId;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * SKU ID
     */
    private String skuId;

    /**
     * 属性类型:1:销售属性;2:非销售属性
     */
    private Integer attrType;

    /**
     * 排序
     */
    private Integer sortNumber;

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