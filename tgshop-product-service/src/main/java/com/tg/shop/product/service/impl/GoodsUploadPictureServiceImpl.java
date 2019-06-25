package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.product.entity.GoodsUploadPicture;
import com.tg.shop.product.mapper.GoodsUploadPictureMapper;
import com.tg.shop.product.service.GoodsUploadPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/21 11:31
 */
@Service
public class GoodsUploadPictureServiceImpl implements GoodsUploadPictureService {

    @Resource
    private GoodsUploadPictureMapper goodsUploadPictureMapper;

    @Override
    public int saveGoodsUploadPicture(GoodsUploadPicture goodsUploadPicture) {
        return goodsUploadPictureMapper.insertSelective(goodsUploadPicture);
    }

    @Override
    public GoodsUploadPicture getPictureById(String pictureId) {
        return goodsUploadPictureMapper.selectByPrimaryKey(pictureId);
    }

    @Override
    public GoodsUploadPicture getByGoodsIdAndSrcMd5(String storeId, String goodsId, String srcMd5) {
        Assert.notNull(storeId,"storeId must not be null");
        Assert.notNull(srcMd5,"srcMd5 must not be null");
        GoodsUploadPicture condition = new GoodsUploadPicture();
        condition.setStoreId(storeId);
        condition.setGoodsId(goodsId);
        condition.setSrcMd5(srcMd5);
        List<GoodsUploadPicture> list = goodsUploadPictureMapper.findListByCondition(condition);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
