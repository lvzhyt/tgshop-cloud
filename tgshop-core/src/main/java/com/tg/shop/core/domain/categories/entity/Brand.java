package com.tg.shop.core.domain.categories.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Brand implements Serializable {
    /**
     * 品牌ID
     */
    private String brandId;

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 英文名
     */
    private String brandNameEn;

    /**
     * logoUrl
     */
    private String logoUrl;

    /**
     * 背景图片
     */
    private String logBackUrl;

    /**
     * 首字母
     */
    private String initial;

    /**
     * 排序权重
     */
    private Integer weight;

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