package com.tg.shop.product.utils;

import com.tg.shop.core.domain.categories.entity.Attribute;
import com.tg.shop.core.utils.CommonDictionary;

/**
 * 获取默认的颜色、尺码属性
 */
public class GoodsAttributeUtil {

    /**
     * 获取商品一级销售属性ID
     * @return
     */
    public static Attribute getGoodsColorAttr(){
        Attribute colorAttr = new Attribute();
        colorAttr.setAttrId(CommonDictionary.GOODS_SPEC_COLOR_ATTR_ID);
        colorAttr.setAttrName("颜色");
        return colorAttr;
    }

    public static Attribute getGoodsSizeAttr(){
        Attribute attr = new Attribute();
        attr.setAttrId(CommonDictionary.GOODS_SPEC_SIZE_ATTR_ID);
        attr.setAttrName("尺码");
        return  attr;
    }

}
