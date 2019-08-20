package com.tg.shop.upload.util;

/**
 * 上传保存目录
 * @author Administrator
 */
public enum  UploadTypeEnum {

    /**
     * 商品图片
     */
   GOODS_PICTURE(0,"goods"),
    /**
     * excel
     */
   EXCEL(1,"excels"),
    /**
     * 会员上传文件
     */
   MEMBER(2,"members");

   private int type;
   private String folder;

   UploadTypeEnum(int type,String folder){
       this.type = type;
       this.folder = folder;
   }

    public int getType() {
        return type;
    }

    public String getFolder() {
        return folder;
    }

    public static UploadTypeEnum getEnumByType(int type){
        for (UploadTypeEnum item : UploadTypeEnum.values()) {
            if(item.getType()==type){
                return item;
            }
        }

       return null;
    }
}
