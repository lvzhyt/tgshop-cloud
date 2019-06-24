package com.tg.shop.core.domain.categories.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Attribute implements Serializable {
    /**
     * 属性ID
     */
    private String attrId;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 显示名称
     */
    private String showName;

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