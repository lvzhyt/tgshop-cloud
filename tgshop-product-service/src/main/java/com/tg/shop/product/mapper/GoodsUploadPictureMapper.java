package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsUploadPicture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsUploadPictureMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String pictureId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(GoodsUploadPicture record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(GoodsUploadPicture record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    GoodsUploadPicture selectByPrimaryKey(String pictureId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsUploadPicture record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(GoodsUploadPicture record);


    List<GoodsUploadPicture> findListByCondition(GoodsUploadPicture condition);
}