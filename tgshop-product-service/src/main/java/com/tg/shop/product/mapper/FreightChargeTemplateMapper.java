package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.FreightChargeTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreightChargeTemplateMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String templateId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(FreightChargeTemplate record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(FreightChargeTemplate record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    FreightChargeTemplate selectByPrimaryKey(String templateId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(FreightChargeTemplate record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(FreightChargeTemplate record);

    List<FreightChargeTemplate> selectFreightChargeTemplateListByCondition(FreightChargeTemplate condition);
}