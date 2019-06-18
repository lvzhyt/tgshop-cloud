package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsUploadPicture implements Serializable {
    /**
     * 商品图片id 主键
     */
    private String pictureId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 图片名称
     */
    private String pictureName;

    /**
     * 图片url
     */
    private String pictureUrl;

    /**
     * 源文件md5
     */
    private String srcMd5;

    /**
     * 图片MD5
     */
    private String picMd5;

    /**
     * 图片大小
     */
    private Integer fileSize;

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