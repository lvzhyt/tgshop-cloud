package com.tg.shop.product.request.param;


import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class UploadSpecAttributePictureParameter {


    /**
     * 商品属性id
     */
    private String goodsAttrValId;

    /**
     * 上传图片id
     */
    private String pictureUrl;

}
