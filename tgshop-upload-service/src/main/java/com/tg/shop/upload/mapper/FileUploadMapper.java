package com.tg.shop.upload.mapper;

import com.tg.shop.core.domain.upload.FileUpload;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileUploadMapper {
    /**
     *
     * @mbg.generated Mon Aug 19 16:58:38 CST 2019
     */
    int deleteByPrimaryKey(String uploadFileId);

    /**
     *
     * @mbg.generated Mon Aug 19 16:58:38 CST 2019
     */
    int insert(FileUpload record);

    /**
     *
     * @mbg.generated Mon Aug 19 16:58:38 CST 2019
     */
    int insertSelective(FileUpload record);

    /**
     *
     * @mbg.generated Mon Aug 19 16:58:38 CST 2019
     */
    FileUpload selectByPrimaryKey(String uploadFileId);

    /**
     *
     * @mbg.generated Mon Aug 19 16:58:38 CST 2019
     */
    int updateByPrimaryKeySelective(FileUpload record);

    /**
     *
     * @mbg.generated Mon Aug 19 16:58:38 CST 2019
     */
    int updateByPrimaryKey(FileUpload record);

    List<FileUpload> findByCondition(FileUpload fileUpload);
}