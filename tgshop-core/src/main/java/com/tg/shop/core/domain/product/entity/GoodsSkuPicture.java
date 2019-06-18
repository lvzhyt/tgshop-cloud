package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsSkuPicture implements Serializable {
    /**
     * 表id
     */
    private String tbId;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * sku_id
     */
    private String skuId;

    /**
     * 图片id
     */
    private String pictureId;

    /**
     * 图片url
     */
    private String pictureDescription;

    /**
     * 类型  1封面图 2 主图 3 详情图
     */
    private Integer pictureType;

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