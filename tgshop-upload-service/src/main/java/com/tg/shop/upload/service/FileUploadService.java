package com.tg.shop.upload.service;


import com.tg.shop.core.domain.upload.FileUpload;

public interface FileUploadService {

    int saveUploadFile(FileUpload fileUpload);


    /**
     * 根据上传文件md5获取文件
     * @param srcMd5
     * @return
     */
    FileUpload getBySrcMd5(String srcMd5);

    FileUpload getFileUploadById(String uploadFileId);
}
