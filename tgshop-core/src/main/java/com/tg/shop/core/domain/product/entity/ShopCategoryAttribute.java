package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ShopCategoryAttribute implements Serializable {
    /**
     * 商家类目属性id
     */
    private String shopCategoryAttributeId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 卖家id
     */
    private String sellerId;

    /**
     * 类目id
     */
    private String categoryId;

    /**
     * 属性id
     */
    private String attributeId;

    /**
     * 是否必填。1：必填；2：非必填
     */
    private Integer optionType;

    /**
     * 属性类型:1:销售属性;0:非销售属性
     */
    private Integer attrType;

    /**
     * 是否多选。1：单选；2：多选
     */
    private Integer selectType;

    /**
     * 属性排序
     */
    private Integer sortNum;

    /**
     * 状态 1，0
     */
    private Integer status;

    /**
     *  json属性值
     */
    private String jsonValue;

    /**
     * 删除状态 1 删除
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