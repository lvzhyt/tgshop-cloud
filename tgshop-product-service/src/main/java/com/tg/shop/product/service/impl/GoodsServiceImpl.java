package com.tg.shop.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.product.mapper.GoodsMapper;
import com.tg.shop.product.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/20 16:48
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
//    @Resource
//    private GoodsEsProducer goodsEsProducer;

    @Override
    public int saveGoods(Goods goods) {
        int count = goodsMapper.insertSelective(goods);
        return count;
    }

    @Override
    public int updateGoodsById(Goods goods) {
        Assert.notNull(goods.getGoodsId(),"goodsId is null");
        int count = goodsMapper.updateByPrimaryKeySelective(goods);
//        goodsEsProducer.send(goods.getGoodsId());
        return count;
    }

    @Override
    public int deleteGoods(Goods goods) {
        Assert.notNull(goods.getGoodsId(),"goodsId is null");
        goods.setIsDel(BaseEntityInfo.STATE_DELETE);
        int count = goodsMapper.updateByPrimaryKeySelective(goods);
        return count;
    }

    @Override
    public Goods getGoodsById(String goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public PageInfo<Goods> findGoodsPageList(PageCondition<Goods> pageCondition) {
        PageHelper.startPage(pageCondition.getPageNum(),pageCondition.getPageSize());
        Goods condition = pageCondition.getCondition();
        List<Goods> list = goodsMapper.findGoodsList(condition);
        PageInfo<Goods> pageInfo = new PageInfo(list);

        return pageInfo;
    }

    @Override
    public Goods getGoodsBySn(String goodsSn) {
        Assert.notNull(goodsSn,"goodsSn must not be null");
        Goods condition = new Goods();
        condition.setGoodsSn(goodsSn);
        List<Goods> list = goodsMapper.findGoodsList(condition);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public PageInfo<Goods> findGoodsWithSkuListPageList(PageCondition<Goods> pageCondition) {
        PageHelper.startPage(pageCondition.getPageNum(),pageCondition.getPageSize());
        Goods condition = pageCondition.getCondition();
        List<Goods> list = goodsMapper.findGoodsWithSkuListPageList(condition);
        PageInfo<Goods> pageInfo = new PageInfo(list);

        return pageInfo;
    }
}
