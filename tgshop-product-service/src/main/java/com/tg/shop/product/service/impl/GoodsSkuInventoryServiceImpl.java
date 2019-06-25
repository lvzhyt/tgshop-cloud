package com.tg.shop.product.service.impl;

import com.tg.shop.core.generator.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.product.entity.GoodsSkuInventory;
import com.tg.shop.core.domain.product.entity.GoodsSkuInventoryLog;
import com.tg.shop.product.mapper.GoodsSkuInventoryLogMapper;
import com.tg.shop.product.mapper.GoodsSkuInventoryMapper;
import com.tg.shop.product.service.GoodsSkuInventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class GoodsSkuInventoryServiceImpl implements GoodsSkuInventoryService {

    @Resource
    private GoodsSkuInventoryMapper goodsSkuInventoryMapper;
    @Resource
    private GoodsSkuInventoryLogMapper goodsSkuInventoryLogMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int saveSkuInventory(GoodsSkuInventory record) {
        return goodsSkuInventoryMapper.insertSelective(record);
    }

    @Override
    public GoodsSkuInventory getBySkuId(String skuId) {
        return goodsSkuInventoryMapper.selectByPrimaryKey(skuId);
    }



    @Override
    @Transactional
    public int updateSkuInventoryByVersion(GoodsSkuInventory record) {
        Assert.notNull(record.getSkuId(),"skuId must not be null");
        Assert.notNull(record.getVersion(),"version must not be null");
        int count = goodsSkuInventoryMapper.updateSkuInventoryByVersion(record);
        if(count>0){
            GoodsSkuInventoryLog inventoryLog = new GoodsSkuInventoryLog();
            BeanUtils.copyProperties(record,inventoryLog);
            inventoryLog.setTbId(idGenerator.nextStringId());
            inventoryLog.setCreator(record.getModifier());
            inventoryLog.setCreateTime(record.getModifyTime());
            goodsSkuInventoryLogMapper.insertSelective(inventoryLog);
        }
        return count;
    }
}
