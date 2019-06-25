package com.tg.shop.product.dao.impl;

import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.product.entity.GoodsSku;
import com.tg.shop.core.domain.product.entity.GoodsSkuInventory;
import com.tg.shop.core.domain.product.entity.GoodsSkuPrice;
import com.tg.shop.product.dao.GoodsSkuDao;
import com.tg.shop.product.mapper.GoodsSkuInventoryMapper;
import com.tg.shop.product.mapper.GoodsSkuMapper;
import com.tg.shop.product.mapper.GoodsSkuPriceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class GoodsSkuDaoImpl implements GoodsSkuDao {
    @Resource
    private GoodsSkuMapper goodsSkuMapper;
    @Resource
    private GoodsSkuInventoryMapper goodsSkuInventoryMapper;
    @Resource
    private GoodsSkuPriceMapper goodsSkuPriceMapper;

    @Override
    public int deleteSku(GoodsSku goodsSku) {
        Assert.notNull(goodsSku.getSkuId(),"SkuId为空");
        goodsSku.setIsDel(BaseEntityInfo.STATE_DELETE);
        return goodsSkuMapper.updateByPrimaryKeySelective(goodsSku);
    }

    @Override
    public int updateSkuById(GoodsSku goodsSku) {
        Assert.notNull(goodsSku.getSkuId(),"SkuId为空");
        return goodsSkuMapper.updateByPrimaryKeySelective(goodsSku);
    }

    @Override
    public List<GoodsSku> findSkuByCondition(GoodsSku condition) {
        return goodsSkuMapper.findSkuByCondition(condition);
    }

    @Override
    public int saveSku(GoodsSku goodsSku) {
        int count = goodsSkuMapper.insertSelective(goodsSku);
        GoodsSkuInventory skuInventory = new GoodsSkuInventory();
        GoodsSkuPrice skuPrice = new GoodsSkuPrice();
        BeanUtils.copyProperties(goodsSku,skuInventory);
        BeanUtils.copyProperties(goodsSku,skuPrice);
        goodsSkuInventoryMapper.insertSelective(skuInventory);
        goodsSkuPriceMapper.insertSelective(skuPrice);
        return count;
    }
}
