package com.tg.shop.upload.service;

import java.io.InputStream;

/**
 * @Author: tg
 * @Date: 2018/12/19 17:41
 */
public interface OssUploadService {

    /**
     * 文件上传api
     * @return
     */
    String upload(InputStream inputStream, String destFile) throws Exception;
}
