package com.tg.shop.upload.service.impl;

import com.tg.shop.core.domain.upload.FileUpload;
import com.tg.shop.upload.dao.FileUploadDao;
import com.tg.shop.upload.mapper.FileUploadMapper;
import com.tg.shop.upload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private FileUploadMapper fileUploadMapper;
    @Autowired
    private FileUploadDao fileUploadDao;


    @Override
    public int saveUploadFile(FileUpload fileUpload) {
        return fileUploadMapper.insertSelective(fileUpload);
    }

    @Override
    public FileUpload getBySrcMd5(String srcMd5) {
        FileUpload fileUpload = new FileUpload();
        fileUpload.setSrcMd5(srcMd5);
        List<FileUpload> list =  fileUploadDao.findByCondition(fileUpload);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public FileUpload getFileUploadById(String uploadFileId) {
        return fileUploadMapper.selectByPrimaryKey(uploadFileId);
    }
}
