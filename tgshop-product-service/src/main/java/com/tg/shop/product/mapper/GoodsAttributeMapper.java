package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsAttribute;
import com.tg.shop.core.domain.product.vo.GoodsAttributeCollectionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsAttributeMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(GoodsAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(GoodsAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    GoodsAttribute selectByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(GoodsAttribute record);


    List<GoodsAttribute> findGoodsAttributeList(GoodsAttribute condition);

    List<GoodsAttributeCollectionVo> findGoodsAttrAndValueVoList(GoodsAttribute condition);
}