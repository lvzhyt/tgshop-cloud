package com.tg.shop.upload.dao;

import com.tg.shop.core.domain.upload.FileUpload;

import java.util.List;

public interface FileUploadDao {
    List<FileUpload> findByCondition(FileUpload fileUpload);
}
