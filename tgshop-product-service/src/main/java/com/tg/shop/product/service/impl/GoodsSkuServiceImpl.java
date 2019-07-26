package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.product.entity.GoodsSku;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuInventoryResultVo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuPriceResultVo;
import com.tg.shop.product.dao.GoodsSkuDao;
import com.tg.shop.product.mapper.GoodsSkuMapper;
import com.tg.shop.product.service.GoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/20 16:48
 */
@Service
public class GoodsSkuServiceImpl implements GoodsSkuService {

    @Resource
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private GoodsSkuDao goodsSkuDao;


    @Override
    public GoodsSku getBySkuId(String id) {
        return goodsSkuMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveSku(GoodsSku goodsSku) {
        return goodsSkuDao.saveSku(goodsSku);
    }

    @Override
    public int deleteSku(GoodsSku goodsSku) {
        int count = goodsSkuDao.deleteSku(goodsSku);
        return count;
    }

    @Override
    public int updateSkuById(GoodsSku goodsSku) {
        int count = goodsSkuDao.updateSkuById(goodsSku);
        return count;
    }

    @Override
    public List<GoodsSku> findSkuByGoodsId(String goodsId) {
        Assert.notNull(goodsId,"goodsId is null");
        GoodsSku condition = new GoodsSku();
        condition.setGoodsId(goodsId);
        condition.setIsDel(BaseEntityInfo.STATE_OK);
        return goodsSkuDao.findSkuByCondition(condition);
    }

    @Override
    public List<GoodsSku> findSkuByCondition(GoodsSku condition) {
        return goodsSkuDao.findSkuByCondition(condition);
    }

    @Override
    public GoodsSku findUniqueBySkuNo(String skuNo) {
        Assert.notNull(skuNo,"findUniqueBySkuNo skuNo is null");
        GoodsSku condition = new GoodsSku();
        condition.setSkuNo(skuNo);
        condition.setIsDel(BaseEntityInfo.STATE_OK);
        List<GoodsSku> list = goodsSkuDao.findSkuByCondition(condition);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public int batchCreateAndDelSku(List<GoodsSku> newList, List<GoodsSku> updateAddList, List<GoodsSku> delList) {
        int count = 0;
        for (GoodsSku sku :
                newList) {
            count += goodsSkuDao.saveSku(sku);
        }
        for (GoodsSku sku :
                updateAddList) {
            count += goodsSkuMapper.updateByPrimaryKeySelective(sku);
        }
        for (GoodsSku sku:delList){
            count += goodsSkuMapper.updateByPrimaryKeySelective(sku);
        }
        return count;
    }

    @Override
    public List<GoodsSkuInventoryResultVo> findSkuInventoryListByGoodsId(String goodsId) {
        return goodsSkuMapper.findSkuInventoryListByGoodsId(goodsId);
    }

    @Override
    public List<GoodsSkuPriceResultVo> findSkuPriceListByGoodsId(String goodsId) {
        return goodsSkuMapper.findSkuPriceListByGoodsId(goodsId);
    }

    @Override
    public List<GoodsSkuDetailResultVo> findSkuDetailListByGoodsId(String goodsId) {
        return goodsSkuMapper.findSkuDetailListByGoodsId(goodsId);
    }

    /**
     * SKU 详情
     * @param skuId
     * @return
     */
    @Override
    public GoodsSkuDetailResultVo findSkuDetailBySkuId(String skuId){
        return goodsSkuMapper.findSkuDetailBySkuId(skuId);
    }
}
