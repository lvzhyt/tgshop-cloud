package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsPicture implements Serializable {
    /**
     * 表id
     */
    private String tId;

    /**
     * sku
     */
    private String sku;

    /**
     * 图片详情内容
     */
    private String pictureDescription;

    /**
     * 类型  1 主图 2 详情图
     */
    private Integer pictureType;

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