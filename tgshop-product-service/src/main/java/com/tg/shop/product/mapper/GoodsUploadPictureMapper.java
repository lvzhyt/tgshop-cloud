package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsUploadPicture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsUploadPictureMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String pictureId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(GoodsUploadPicture record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(GoodsUploadPicture record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    GoodsUploadPicture selectByPrimaryKey(String pictureId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsUploadPicture record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(GoodsUploadPicture record);
}