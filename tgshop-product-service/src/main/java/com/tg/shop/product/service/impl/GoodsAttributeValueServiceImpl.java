package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.product.entity.GoodsAttributeValue;
import com.tg.shop.product.mapper.GoodsAttributeValueMapper;
import com.tg.shop.product.service.GoodsAttributeValueService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsAttributeValueServiceImpl implements GoodsAttributeValueService {

    @Resource
    private GoodsAttributeValueMapper goodsAttributeValueMapper;

    @Override
    public int saveGoodsAttributeValue(GoodsAttributeValue record) {
        return goodsAttributeValueMapper.insertSelective(record);
    }

    @Override
    public GoodsAttributeValue getGoodsAttributeValueById(String tid) {
        return goodsAttributeValueMapper.selectByPrimaryKey(tid);
    }

    @Override
    public int batchUpdateGoodsAttribute(List<GoodsAttributeValue> list) {
        int count =0;
        for (GoodsAttributeValue item:
             list) {
            item.setIsDel(BaseEntityInfo.STATE_DELETE);
            count+=goodsAttributeValueMapper.updateByPrimaryKeySelective(item);
        }
        return count;
    }


    @Override
    public List<GoodsAttributeValue> findGoodsAttributeValueList(GoodsAttributeValue condition) {
        return goodsAttributeValueMapper.findGoodsAttributeValueList(condition);
    }

    @Override
    public GoodsAttributeValue findGoodsAttrValUnique(String goodsId, String attrId, String valueId) {
        GoodsAttributeValue condition = new GoodsAttributeValue();
        condition.setGoodsId(goodsId);
        condition.setAttrId(attrId);
        condition.setValueId(valueId);
        List<GoodsAttributeValue> list = goodsAttributeValueMapper.findGoodsAttributeValueList(condition);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

}
