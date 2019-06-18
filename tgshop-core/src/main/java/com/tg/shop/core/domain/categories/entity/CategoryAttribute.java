package com.tg.shop.core.domain.categories.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CategoryAttribute implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 类目ID
     */
    private String categoryId;

    /**
     * 属性ID
     */
    private String attributeId;

    /**
     * 属性类型:1:销售属性;0:非销售属性
     */
    private Integer attrType;

    /**
     * 是否必填。1：必填；2：非必填
     */
    private Integer optionType;

    /**
     * 是否多选。1：单选；2：多选
     */
    private Integer selectType;

    /**
     * 排序号。越小越靠前
     */
    private Integer sortNumber;

    /**
     *  json属性值
     */
    private String jsonValue;

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