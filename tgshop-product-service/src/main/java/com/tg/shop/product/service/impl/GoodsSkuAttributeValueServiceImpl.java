package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.product.entity.GoodsSkuAttributeValue;
import com.tg.shop.product.mapper.GoodsSkuAttributeValueMapper;
import com.tg.shop.product.service.GoodsSkuAttributeValueService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsSkuAttributeValueServiceImpl implements GoodsSkuAttributeValueService {

    @Resource
    private GoodsSkuAttributeValueMapper goodsSkuAttributeValueMapper;
    @Override
    public int saveGoodsSkuAttributeValue(GoodsSkuAttributeValue record) {
        return goodsSkuAttributeValueMapper.insertSelective(record);
    }

    @Override
    public GoodsSkuAttributeValue getGoodsSkuAttributeValueById(String tid) {
        return goodsSkuAttributeValueMapper.selectByPrimaryKey(tid);
    }

    @Override
    public GoodsSkuAttributeValue findBySkuIdAttrName(String skuId, String attrId, String attrValue) {
        GoodsSkuAttributeValue condition = new GoodsSkuAttributeValue();
        condition.setSkuId(skuId);
        condition.setAttrId(attrId);
        condition.setAttrValue(attrValue);
        List<GoodsSkuAttributeValue> list =goodsSkuAttributeValueMapper.findGoodsSkuAttributeValueList(condition);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<GoodsSkuAttributeValue> findGoodsSkuAttributeValueList(GoodsSkuAttributeValue record) {
        return goodsSkuAttributeValueMapper.findGoodsSkuAttributeValueList(record);
    }
}
