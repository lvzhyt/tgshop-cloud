package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.FreightChargeTemplate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FreightChargeTemplateMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String templateId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(FreightChargeTemplate record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(FreightChargeTemplate record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    FreightChargeTemplate selectByPrimaryKey(String templateId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(FreightChargeTemplate record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(FreightChargeTemplate record);
}