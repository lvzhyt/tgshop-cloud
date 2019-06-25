package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.product.entity.GoodsSkuPrice;
import com.tg.shop.core.domain.product.entity.GoodsSkuPriceLog;
import com.tg.shop.product.mapper.GoodsSkuPriceLogMapper;
import com.tg.shop.product.mapper.GoodsSkuPriceMapper;
import com.tg.shop.product.service.GoodsSkuPriceService;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class GoodsSkuPriceServiceImpl implements GoodsSkuPriceService {

    @Resource
    private GoodsSkuPriceMapper goodsSkuPriceMapper;
    @Resource
    private GoodsSkuPriceLogMapper goodsSkuPriceLogMapper;

    @Override
    public int saveSkuPrice(GoodsSkuPrice record) {
        return goodsSkuPriceMapper.insertSelective(record);
    }

    @Override
    public GoodsSkuPrice getBySkuId(String skuId) {
        return goodsSkuPriceMapper.selectByPrimaryKey(skuId);
    }

    @Override
    public int updateSkuPrice(GoodsSkuPrice record, GoodsSkuPriceLog priceLog) {
        Assert.notNull(record.getSkuId(),"skuId must not be null");
        int count = goodsSkuPriceMapper.updateByPrimaryKeySelective(record);
        if(count>0){
            goodsSkuPriceLogMapper.insertSelective(priceLog);
        }
        return count;
    }
}
