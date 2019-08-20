package com.tg.shop.upload.controller;

import com.tg.shop.core.domain.auth.entity.Member;
import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.domain.auth.entity.Store;
import com.tg.shop.core.domain.upload.FileUpload;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.CacheMemberHolderLocal;
import com.tg.shop.core.utils.CacheSellerHolderLocal;
import com.tg.shop.core.utils.CacheStoreHolderLocal;
import com.tg.shop.upload.service.FileUploadService;
import com.tg.shop.upload.service.OssUploadService;
import com.tg.shop.upload.util.UploadTypeEnum;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件上传服务
 * @author Administrator
 */
@RestController
public class UploadController {

    private final int IMAGE_WIDTH = 800;
    private final int IMAGE_HEIGHT = 800;


    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private OssUploadService ossUploadService;
    @Autowired
    private IdGenerator idGenerator;

    /**
     * 上传文件
     * @param files
     * @param refId
     * @param type
     * @return
     * @throws Exception
     */
    @ApiOperation("上传文件")
    @PostMapping(value = "/uploadFiles",headers = "content-type=multipart/form-data")
    public ResultObject uploadFiles(
            @ApiParam(value = "上传的文件") @RequestParam(value = "files") MultipartFile[] files,
            @ApiParam(value = "商品Id") @RequestParam(value = "refId",required = false) String refId,
            @ApiParam(value = "备注") @RequestParam(value = "remark",required = false) String remark,
            @ApiParam(value = "图片类型") @RequestParam(value = "type",required = false,defaultValue = "0") Integer type) throws Exception {


        Member member = CacheMemberHolderLocal.getMember();
        Store store = CacheStoreHolderLocal.getStore();
        Seller seller = CacheSellerHolderLocal.getSeller();
        String ownerId = "0";
        String creator = "none";
        String userFolder = "anon";
        if(member!=null){
            creator = member.getMemberId();
            userFolder = member.getPhone()!=null?member.getPhone():member.getMemberId();
            ownerId = member.getMemberId();
        }
        if(seller!=null){
            creator = seller.getSellerId();
            userFolder = store.getStoreCode();
            ownerId=store.getStoreId();
        }

        String uploadTypeFolder = "type";
        UploadTypeEnum uploadTypeEnum = UploadTypeEnum.getEnumByType(type);
        if(uploadTypeEnum!=null){
            uploadTypeFolder = uploadTypeEnum.getFolder();
        }

        List<FileUpload> list = new ArrayList<>();
        for (MultipartFile file :
                files) {
            byte[] uploadBytes = file.getBytes();
            String srcMd5 = DigestUtils.md5DigestAsHex(uploadBytes);
            FileUpload record = fileUploadService.getBySrcMd5(srcMd5);
            if(record!=null){
                list.add(record);
                continue;
            }
            String dayDir = DateFormatUtils.format(new Date(),"yyyyMMdd");
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String destFileName = Long.toHexString(System.currentTimeMillis())+"."+extension;
            String destFileName200x200 = destFileName+"_200x200."+extension;

            // 用户 + 文件类型 + 日期 + 文件名
            String relativePath = userFolder+"/"+uploadTypeFolder+"/"+dayDir;
            String destFile = relativePath+"/"+destFileName;
            String destFile200 = relativePath+"/"+destFileName200x200;



            /**
             * 图片压缩
             * 500x500 banner 主图 详情图
             * 200x200 商品列表图
             * 其他尺寸 oss 压缩
             */
            byte[] bytesZip = zipImageFile(uploadBytes,IMAGE_WIDTH,IMAGE_HEIGHT);
//            byte[] bytes200 = zipImageFile(uploadBytes,200,200);
            InputStream inputStream800 = new ByteArrayInputStream(bytesZip);
//            InputStream inputStream200 = new ByteArrayInputStream(bytes200);

            String url = ossUploadService.upload(inputStream800,destFile);
            // 上传200x200图片
//            String url200 = ossUploadService.upload(inputStream200,destFile200);
            // 保存图片
            FileUpload fileUpload = new FileUpload();
            fileUpload.setUploadFileId(idGenerator.nextStringId());
            fileUpload.setRefId(refId);

            fileUpload.setOwnerId(ownerId);
            fileUpload.setFileSize(bytesZip.length);
            fileUpload.setUploadType(type);
            String md5 = DigestUtils.md5DigestAsHex(bytesZip);
            fileUpload.setFileMd5(md5);
            fileUpload.setSrcMd5(srcMd5);
            fileUpload.setCreator(seller.getSellerId());
            fileUpload.setFileName(file.getOriginalFilename());
            fileUpload.setFileUrl(url);
            fileUpload.setFileExt(extension);
            fileUpload.setCreator(creator);
            fileUpload.setCreateTime(new Date());
            fileUpload.setRemark(remark);
            fileUploadService.saveUploadFile(fileUpload);
            list.add(fileUpload);
        }


        return new ResultObject<>(list);
    }

    /**
     * 压缩文件
     * @param bytes
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    private byte[] zipImageFile(byte[] bytes,int width,int height) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(inputStream)
                .size(width,height)
                .toOutputStream(outputStream);
        return outputStream.toByteArray();
    }
}
