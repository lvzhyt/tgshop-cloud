package com.tg.shop.categories.service;

import com.tg.shop.core.domain.categories.entity.AttributeValue;

import java.util.List;

/**
 * @Author: tg
 * @Date: 2018/12/19 16:40
 */
public interface AttributeValueService {

    int saveAttributeValue(AttributeValue attributeValue);

    int updateAttributeValue(AttributeValue attributeValue);

    List<AttributeValue> findAttributeValueList(AttributeValue condition);

}
