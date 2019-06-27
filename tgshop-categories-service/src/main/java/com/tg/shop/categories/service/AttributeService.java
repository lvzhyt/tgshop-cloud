package com.tg.shop.categories.service;



import com.tg.shop.core.domain.categories.entity.Attribute;

/**
 * @Author: tg
 * @Date: 2018/12/19 16:35
 */
public interface AttributeService {

    int saveAttribute(Attribute attribute);

    int deleteAttribute(Attribute attribute);

    Attribute getAttributeById(String id);

//    List<Attribute> findAttributeList(Attribute condition);

}
