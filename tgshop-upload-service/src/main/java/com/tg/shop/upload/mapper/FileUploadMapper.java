package com.tg.shop.upload.mapper;

import com.tg.shop.upload.entity.FileUpload;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileUploadMapper {
    /**
     *
     * @mbg.generated Mon Aug 19 16:17:59 CST 2019
     */
    int deleteByPrimaryKey(String pictureId);

    /**
     *
     * @mbg.generated Mon Aug 19 16:17:59 CST 2019
     */
    int insert(FileUpload record);

    /**
     *
     * @mbg.generated Mon Aug 19 16:17:59 CST 2019
     */
    int insertSelective(FileUpload record);

    /**
     *
     * @mbg.generated Mon Aug 19 16:17:59 CST 2019
     */
    FileUpload selectByPrimaryKey(String pictureId);

    /**
     *
     * @mbg.generated Mon Aug 19 16:17:59 CST 2019
     */
    int updateByPrimaryKeySelective(FileUpload record);

    /**
     *
     * @mbg.generated Mon Aug 19 16:17:59 CST 2019
     */
    int updateByPrimaryKey(FileUpload record);
}