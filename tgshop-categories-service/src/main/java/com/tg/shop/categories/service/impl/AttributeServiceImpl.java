package com.tg.shop.categories.service.impl;

import com.tg.shop.categories.mapper.AttributeMapper;
import com.tg.shop.categories.service.AttributeService;
import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.categories.entity.Attribute;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Resource
    private AttributeMapper attributeMapper;

    @Override
    public int saveAttribute(Attribute attribute) {
        return attributeMapper.insertSelective(attribute);
    }

    @Override
    public int deleteAttribute(Attribute attribute) {
        Assert.notNull(attribute.getAttrId(),"attrId is null");
        attribute.setIsDel(BaseEntityInfo.STATE_DELETE);
        return attributeMapper.updateByPrimaryKeySelective(attribute);
    }

    @Override
    public Attribute getAttributeById(String id) {
        return attributeMapper.selectByPrimaryKey(id);
    }
}
