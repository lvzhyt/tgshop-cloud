package com.tg.shop.product.service;

import com.tg.shop.core.domain.ResultState;
import com.tg.shop.core.domain.product.entity.FreightChargeTemplate;

import java.util.List;

public interface FreightChargeTemplateService {

    ResultState saveFreightChargeTemplate(FreightChargeTemplate record);

    ResultState deleteFreightChargeTemplate(FreightChargeTemplate record);

    FreightChargeTemplate getFreightChargeTemplateById(String id);

    List<FreightChargeTemplate> findFreightChargeTemplateList(FreightChargeTemplate condition);


}
