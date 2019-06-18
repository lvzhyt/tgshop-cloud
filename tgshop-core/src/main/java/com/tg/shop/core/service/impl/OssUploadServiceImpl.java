package com.tg.shop.core.service.impl;

import com.aliyun.oss.OSSClient;
import com.tg.shop.core.config.OssProperties;
import com.tg.shop.core.service.OssUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @Author: tg
 * @Date: 2018/12/20 12:01
 */
@Slf4j
@Component
@Service
public class OssUploadServiceImpl implements OssUploadService {

    @Autowired
    private OssProperties ossProperties;


    @Override
    public String upload(InputStream inputStream, String destFile) throws Exception{
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getAccessKeySecret();
        String pubServer = ossProperties.getPubService();

        String objectName= destFile;

        try{
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            String bucketName = ossProperties.getBucketName();
            ossClient.putObject(bucketName, objectName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
        }catch (Exception ex){
            log.error("oss 上传文件异常."+ ex.getMessage(),ex);
            throw new Exception(ex);
        }
        String url = pubServer+"/"+objectName;
        return url;
    }



}
