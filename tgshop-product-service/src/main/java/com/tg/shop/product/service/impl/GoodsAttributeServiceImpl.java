package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.product.entity.GoodsAttribute;
import com.tg.shop.core.domain.product.vo.GoodsAttributeCollectionVo;
import com.tg.shop.product.mapper.GoodsAttributeMapper;
import com.tg.shop.product.service.GoodsAttributeService;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsAttributeServiceImpl implements GoodsAttributeService {

    @Resource
    private GoodsAttributeMapper goodsAttributeMapper;

    @Override
    public int saveGoodsAttribute(GoodsAttribute record) {
        return goodsAttributeMapper.insertSelective(record);
    }

    @Override
    public GoodsAttribute getGoodsAttributeById(String tid) {
        return goodsAttributeMapper.selectByPrimaryKey(tid);
    }

    @Override
    public GoodsAttribute findByGoodsIdAttrName(String goodsId, String attrName) {
        Assert.notNull(goodsId,"goodsId is null");
        GoodsAttribute condition = new GoodsAttribute();
        condition.setGoodsId(goodsId);
        condition.setAttrName(attrName);
        List<GoodsAttribute> list = goodsAttributeMapper.findGoodsAttributeList(condition);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<GoodsAttribute> findGoodsAttributeList(GoodsAttribute condition) {
        Assert.notNull(condition.getGoodsId(),"goodsId is null");
        return goodsAttributeMapper.findGoodsAttributeList(condition);
    }

    @Override
    public List<GoodsAttributeCollectionVo> findGoodsAttrAndValueVoList(GoodsAttribute condition) {
        Assert.notNull(condition.getGoodsId(),"goodsId is null");
        return goodsAttributeMapper.findGoodsAttrAndValueVoList(condition);
    }
}
