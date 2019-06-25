package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.product.entity.GoodsSkuAttribute;
import com.tg.shop.product.mapper.GoodsSkuAttributeMapper;
import com.tg.shop.product.service.GoodsSkuAttributeService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsSkuAttributeServiceImpl implements GoodsSkuAttributeService {

    @Resource
    private GoodsSkuAttributeMapper goodsSkuAttributeMapper;

    @Override
    public int saveGoodsSkuAttribute(GoodsSkuAttribute record) {
        return goodsSkuAttributeMapper.insertSelective(record);
    }

    @Override
    public GoodsSkuAttribute getGoodsSkuAttributeById(String tid) {
        return goodsSkuAttributeMapper.selectByPrimaryKey(tid);
    }

    @Override
    public GoodsSkuAttribute findBySkuIdAttrName(String skuId, String attrName) {
        GoodsSkuAttribute condition = new GoodsSkuAttribute();
        condition.setSkuId(skuId);
        condition.setAttrName(attrName);
        List<GoodsSkuAttribute> list = goodsSkuAttributeMapper.findGoodsSkuAttributeList(condition);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<GoodsSkuAttribute> findGoodsSkuAttributeList(GoodsSkuAttribute condition) {
        return goodsSkuAttributeMapper.findGoodsSkuAttributeList(condition);
    }
}
