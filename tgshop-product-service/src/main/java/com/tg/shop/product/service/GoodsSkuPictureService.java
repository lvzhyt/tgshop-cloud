package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.GoodsSkuPicture;

import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/20 16:47
 */
public interface GoodsSkuPictureService {

    GoodsSkuPicture getSkuPictureById(String id);

    int saveSkuPicture(GoodsSkuPicture record);

    int shiftDeleteSkuPicture(String id);

    int shiftDeleteBySkuIdAndType(String skuId, int type);

    int updateSkuPictureById(GoodsSkuPicture record);


    List<GoodsSkuPicture> getSkuPictureListByCondition(GoodsSkuPicture condition);
}
