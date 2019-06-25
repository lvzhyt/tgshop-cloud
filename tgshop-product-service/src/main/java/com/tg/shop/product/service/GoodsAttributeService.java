package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.GoodsAttribute;
import com.tg.shop.core.domain.product.vo.GoodsAttributeCollectionVo;

import java.util.List;

public interface GoodsAttributeService {

    int saveGoodsAttribute(GoodsAttribute record);

    GoodsAttribute getGoodsAttributeById(String tid);

    GoodsAttribute findByGoodsIdAttrName(String goodsId, String attrName);

    List<GoodsAttribute> findGoodsAttributeList(GoodsAttribute goodsAttribute);

    List<GoodsAttributeCollectionVo> findGoodsAttrAndValueVoList(GoodsAttribute goodsAttribute);

}
