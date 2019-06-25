package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.product.entity.ShopAttributeValue;
import com.tg.shop.product.mapper.ShopAttributeValueMapper;
import com.tg.shop.product.service.ShopAttributeValueService;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopAttributeValueServiceImpl implements ShopAttributeValueService {
    @Resource
    private ShopAttributeValueMapper shopAttributeValueMapper;

    @Override
    public ShopAttributeValue getShopAttributeValueById(String id) {
        return shopAttributeValueMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveShopAttributeValue(ShopAttributeValue record) {
        return shopAttributeValueMapper.insertSelective(record);
    }

    @Override
    public int deleteShopAttributeValue(ShopAttributeValue record) {
        Assert.notNull(record.getAttrValueId(),"deleteShopAttributeValue id is null");
        record.setIsDel(BaseEntityInfo.STATE_DELETE);
        return shopAttributeValueMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ShopAttributeValue> findListByCondition(ShopAttributeValue condition) {
        return shopAttributeValueMapper.findListByCondition(condition);
    }


    @Override
    public ShopAttributeValue findShopAttrValUnique(String storeId, String attrId, String attrValue) {
        Assert.notNull(storeId,"storeId is null");
        Assert.notNull(attrId,"attrId is null");
        Assert.notNull(attrValue,"attrValue is null");
        ShopAttributeValue condition = new ShopAttributeValue();
        condition.setStoreId(storeId);
        condition.setAttributeId(attrId);
        condition.setAttributeValue(attrValue);
        List<ShopAttributeValue> list = shopAttributeValueMapper.findListByCondition(condition);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
