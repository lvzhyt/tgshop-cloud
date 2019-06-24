package com.tg.shop.core.domain.categories.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CategoryBrand implements Serializable {
    /**
     * id
     */
    private String categoryBrandId;

    /**
     * 品牌id
     */
    private String brandId;

    /**
     * 二级类目id
     */
    private String categoryLev2Id;

    /**
     * 三级类目id
     */
    private String categoryLev3Id;

    /**
     * 排序
     */
    private Integer sortNum;

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