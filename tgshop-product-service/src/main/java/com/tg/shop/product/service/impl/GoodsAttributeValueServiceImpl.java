package com.tg.shop.product.service.impl;

import com.tg.shop.core.domain.product.entity.GoodsSku;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.product.dao.GoodsSkuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.product.entity.GoodsAttributeValue;
import com.tg.shop.product.mapper.GoodsAttributeValueMapper;
import com.tg.shop.product.service.GoodsAttributeValueService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GoodsAttributeValueServiceImpl implements GoodsAttributeValueService {

    @Resource
    private GoodsAttributeValueMapper goodsAttributeValueMapper;

    @Autowired
    private GoodsSkuDao goodsSkuDao;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultObject deleteSpecAttrValue(GoodsAttributeValue goodsAttributeValue, List<GoodsSku> goodsSkuList) {
        Assert.notNull(goodsAttributeValue,"goodsAttributeValue is null");
        goodsAttributeValue.setIsDel(BaseEntityInfo.STATE_DELETE);
        int result = goodsAttributeValueMapper.updateByPrimaryKeySelective(goodsAttributeValue);
        if(!goodsSkuList.isEmpty()){
            for (GoodsSku goodsSku :
                    goodsSkuList) {
                goodsSku.setModifyTime(new Date());
                goodsSku.setModifier(goodsAttributeValue.getModifier());
                goodsSkuDao.deleteSku(goodsSku);
            }
        }
        return new ResultObject<>(result);
    }

}
