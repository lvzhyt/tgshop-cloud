package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.product.entity.GoodsSkuPicture;
import com.tg.shop.product.mapper.GoodsSkuPictureMapper;
import com.tg.shop.product.service.GoodsSkuPictureService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsSkuPictureServiceImpl implements GoodsSkuPictureService {

    @Resource
    private GoodsSkuPictureMapper goodsSkuPictureMapper;

    @Override
    public GoodsSkuPicture getSkuPictureById(String id) {
        return goodsSkuPictureMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveSkuPicture(GoodsSkuPicture record) {
        return goodsSkuPictureMapper.insertSelective(record);
    }

    @Override
    public int shiftDeleteSkuPicture(String id) {
        return goodsSkuPictureMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int shiftDeleteBySkuIdAndType(String skuId,int type) {
        GoodsSkuPicture condition = new GoodsSkuPicture();
        condition.setSkuId(skuId);
        condition.setPictureType(type);
        List<GoodsSkuPicture> list = goodsSkuPictureMapper.getSkuPictureListByCondition(condition);
        int count = 0;
        for (GoodsSkuPicture item :
                list) {
           count += goodsSkuPictureMapper.deleteByPrimaryKey(item.getTbId());
        }
        return count;
    }

    @Override
    public int updateSkuPictureById(GoodsSkuPicture record) {
        return goodsSkuPictureMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<GoodsSkuPicture> getSkuPictureListByCondition(GoodsSkuPicture condition) {
        return goodsSkuPictureMapper.getSkuPictureListByCondition(condition);
    }
}
