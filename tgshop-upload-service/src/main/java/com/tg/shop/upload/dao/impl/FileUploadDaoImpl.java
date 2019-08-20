package com.tg.shop.upload.dao.impl;

import com.tg.shop.core.domain.upload.FileUpload;
import com.tg.shop.upload.dao.FileUploadDao;
import com.tg.shop.upload.mapper.FileUploadMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class FileUploadDaoImpl implements FileUploadDao {

    @Resource
    private FileUploadMapper fileUploadMapper;

    @Override
    public List<FileUpload> findByCondition(FileUpload fileUpload) {
        return fileUploadMapper.findByCondition(fileUpload);
    }
}
