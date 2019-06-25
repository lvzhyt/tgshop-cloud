package com.tg.shop.product.service.impl;

import org.springframework.stereotype.Service;
import com.tg.shop.core.domain.ResultState;
import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.product.entity.FreightChargeTemplate;
import com.tg.shop.product.mapper.FreightChargeTemplateMapper;
import com.tg.shop.product.service.FreightChargeTemplateService;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FreightChargeTemplateServiceImpl implements FreightChargeTemplateService {

    @Resource
    private FreightChargeTemplateMapper freightChargeTemplateMapper;

    @Override
    public ResultState saveFreightChargeTemplate(FreightChargeTemplate record) {
        int count = freightChargeTemplateMapper.insertSelective(record);
        return new ResultState(count);
    }

    @Override
    public ResultState deleteFreightChargeTemplate(FreightChargeTemplate record) {
        Assert.notNull(record.getTemplateId(),"template is null");
        record.setIsDel(BaseEntityInfo.STATE_DELETE);
        int count = freightChargeTemplateMapper.updateByPrimaryKeySelective(record);
        return new ResultState(count);
    }

    @Override
    public FreightChargeTemplate getFreightChargeTemplateById(String id) {
        return freightChargeTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FreightChargeTemplate> findFreightChargeTemplateList(FreightChargeTemplate condition) {
        return freightChargeTemplateMapper.selectFreightChargeTemplateListByCondition(condition);
    }
}
