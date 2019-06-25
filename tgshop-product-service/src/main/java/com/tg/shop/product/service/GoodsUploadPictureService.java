package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.GoodsUploadPicture;

/**
 * @Author: tg
 * @Date: 2019/3/21 11:31
 */
public interface GoodsUploadPictureService {

    int saveGoodsUploadPicture(GoodsUploadPicture goodsUploadPicture);

    GoodsUploadPicture getPictureById(String pictureId);

    GoodsUploadPicture getByGoodsIdAndSrcMd5(String storeId, String goodsId, String srcMd5);
}
