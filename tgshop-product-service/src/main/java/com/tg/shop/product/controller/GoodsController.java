package com.tg.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.ResultState;
import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.domain.auth.entity.Store;
import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.categories.entity.Attribute;
import com.tg.shop.core.domain.product.entity.*;
import com.tg.shop.core.domain.product.info.GoodsInfo;
import com.tg.shop.core.domain.product.vo.ShopAttributeValueSimpleVo;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.domain.util.PageResult;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.*;
import com.tg.shop.core.validate.InsertValid;
import com.tg.shop.product.config.ShopConfigureProperties;
import com.tg.shop.product.request.param.*;
import com.tg.shop.product.service.*;
import com.tg.shop.product.utils.GoodsAttributeUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.tg.shop.core.domain.ResultState.MESSAGE_TYPE_SUCCESS;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsSkuService goodsSkuService;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private ShopAttributeValueService shopAttributeValueService;
    @Autowired
    private GoodsAttributeValueService goodsAttributeValueService;
    @Autowired
    private GoodsUploadPictureService goodsUploadPictureService;
    @Autowired
    private OssUploadService ossUploadService;
    @Autowired
    private ShopConfigureProperties shopConfigureProperties;

    @GetMapping("/getGoodsById")
    public ResultObject<Goods> getGoodsById(String goodsId){
        Goods goods =  goodsService.getGoodsById(goodsId);
        return new ResultObject<>(goods);
    }

    @GetMapping("/getGoodsBySn")
    public ResultObject<Goods> getGoodsBySn(@RequestParam String goodsSn){
        Goods goods =  goodsService.getGoodsBySn(goodsSn);
        if(goods==null){
            return new ResultObject<>(ErrorCode.EMPTY_DATA_ERROR);
        }
        return new ResultObject<>(goods);
    }

    @ApiOperation("商家商品列表")
    @PostMapping("/findSellerGoodsPageList")
     public ResultObject<PageResult<Goods>> findSellerGoodsPageList(@RequestBody PageCondition<Goods> pageCondition){
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家没有登录");
        Goods goods = pageCondition.getCondition();
        if(goods==null){
            goods = new Goods();
            pageCondition.setCondition(goods);
        }
        goods.setSellerId(CacheSellerHolderLocal.getSeller().getSellerId());
        JacksonPojoUtil.convertEmptyStringToNull(pageCondition.getCondition());
        PageInfo<Goods> page =   goodsService.findGoodsWithSkuListPageList(pageCondition);
        return new ResultObject<>(new PageResult<>(page));
    }

    @ApiOperation("商品列表")
    @PostMapping("/findGoodsPageList")
    public ResultObject<PageResult<Goods>> findGoodsPageList(@RequestBody PageCondition<Goods> pageCondition){
        JacksonPojoUtil.convertEmptyStringToNull(pageCondition.getCondition());
        PageInfo<Goods> page =  goodsService.findGoodsPageList(pageCondition);
        return new ResultObject<>(new PageResult<>(page));
    }

    /**
     * 新增商品 创建商品
     * @param goods
     * @param bindingResult
     * @return
     */
    @PostMapping("/addGoods")
    public ResultObject<Goods> addGoods(@RequestBody @Validated(InsertValid.class) Goods goods, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResultObject(ErrorCode.REQUEST_PARAM_ERROR,bindingResult.getFieldErrors());
        }
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家没有登录");
        JacksonPojoUtil.convertEmptyStringToNull(goods);
        if(goods.getSpecOpen()!=null && goods.getSpecOpen()==0){
            goods.setSpecSizeOpen(0);
        }
        String sellerId = CacheSellerHolderLocal.getSeller().getSellerId();
        Store store = CacheStoreHolderLocal.getStore();
        if(StringUtils.isEmpty(goods.getGoodsSn())){
            goods.setGoodsSn("SN"+idGenerator.nextStringId());
        }
        goods.setGoodsId(idGenerator.nextStringId());
        goods.setSellerId(store.getSellerId());
        goods.setStoreId(store.getStoreId());
        goods.setCreator(sellerId);
        goods.setCreateTime(new Date());
        goods.setShopName(store.getStoreName());

        // 未开启规格属性 创建sku
        if(goods.getSpecOpen()==null||goods.getSpecOpen()==0){
            GoodsSku sku = new GoodsSku();
            sku.setSkuId(idGenerator.nextStringId());
            sku.setGoodsId(goods.getGoodsId());
            sku.setSkuNo(idGenerator.nextStringId());
            String skuGoodsName = getSkuName(goods,null,null);
            sku.setSkuGoodsName(skuGoodsName);
            sku.setSellerId(store.getSellerId());
            sku.setStoreId(store.getStoreId());
            sku.setCreator(sellerId);
            sku.setCreateTime(new Date());
            goodsService.saveGoodsAndSku(goods,sku);
        }else{
            goodsService.saveGoods(goods);
        }
        return new ResultObject<>(goods);
    }


    private String getSkuName(Goods goods,String colorAttrValName,String sizeAttrValName){
        String brandName = "";
        if(StringUtils.isNotEmpty(goods.getBrandId())&& (!"0".equals(goods.getBrandId()))){
            brandName = goods.getBrandName()+" ";
        }
        String categoryName = "";
        if(StringUtils.isNotEmpty(goods.getCategoryId())&& (!"0".equals(goods.getCategoryId()))){
            brandName = goods.getCategoryName()+" ";
        }
        colorAttrValName = StringUtils.isNotEmpty(colorAttrValName)?" "+colorAttrValName:"";
        sizeAttrValName = StringUtils.isNotEmpty(sizeAttrValName)?" "+sizeAttrValName:"";
        String skuName = brandName+categoryName+goods.getGoodsName()+colorAttrValName+sizeAttrValName;
        return skuName;
    }



    /**
     * 添加商品规格
     * @param goodsSpecAttrForm
     * @param bindingResult
     * @return
     */
    @ApiOperation("添加商品规格")
    @PostMapping("/addSpecAttr")
    public JSONObject addGoodsSpecAttr(@RequestBody @Valid AddGoodsSpecAttrParam goodsSpecAttrForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return JSONResultUtil.createJsonObject(ErrorCodeFeature.VALID_BIND_ERROR,bindingResult.getFieldErrors());
        }
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家没有登录");
        String sellerId = CacheSellerHolderLocal.getSeller().getSellerId();
        Store store = CacheStoreHolderLocal.getStore();
        String storeId = store.getStoreId();
        String goodsId = goodsSpecAttrForm.getGoodsId();
        String attrValue = goodsSpecAttrForm.getAttrValue();
        Goods goods = goodsService.getGoodsById(goodsId);
        Assert.notNull(goods,"goodsId 不存在. "+goodsId);
        Attribute itemAttr = GoodsAttributeUtil.getGoodsColorAttr();
        if(goodsSpecAttrForm.getAttrLevel()==2){
            // 尺码属性值
            itemAttr = GoodsAttributeUtil.getGoodsSizeAttr();
        }
        String attrId = itemAttr.getAttrId();
        ShopAttributeValue shopAttributeValue = shopAttributeValueService.findShopAttrValUnique(store.getStoreId(),attrId,attrValue);
        if(shopAttributeValue==null){
            // 创建店铺属性值
            shopAttributeValue = new ShopAttributeValue();
            shopAttributeValue.setAttrValueId(idGenerator.nextStringId());
            shopAttributeValue.setStoreId(storeId);
            shopAttributeValue.setAttributeName(itemAttr.getAttrName());
            shopAttributeValue.setAttributeId(itemAttr.getAttrId());
            shopAttributeValue.setAttributeValue(attrValue);
            shopAttributeValue.setCreateTime(new Date());
            shopAttributeValue.setCreator(sellerId);
            int count = shopAttributeValueService.saveShopAttributeValue(shopAttributeValue);
            if(count==0){
                return JSONResultUtil.createJsonObject("创店铺属性值失败");
            }
        }
        String attrValueId = shopAttributeValue.getAttrValueId();
        GoodsAttributeValue goodsAttributeValue =  goodsAttributeValueService.findGoodsAttrValUnique(goodsId,attrId,attrValueId);
        if(goodsAttributeValue!=null){
            return JSONResultUtil.createJsonObject(ErrorCodeFeature.DATA_UNIQUE_ERROR,attrValue);
        }
        GoodsAttributeValue record = new GoodsAttributeValue();
        record.setTbId(idGenerator.nextStringId());
        record.setGoodsId(goodsId);
        record.setAttrId(attrId);
        record.setAttrName(itemAttr.getAttrName());
        record.setValueId(attrValueId);
        record.setAttrValueName(attrValue);
        record.setSortNumber(goodsSpecAttrForm.getSortNum());
        record.setCreateTime(new Date());
        record.setCreator(sellerId);
        // 保存商品属性值
        int count = goodsAttributeValueService.saveGoodsAttributeValue(record);
        if(count==0){
            return JSONResultUtil.createJsonObject(new ResultState(count));
        }
        // 只开启一级属性 创建属性对应sku
        if(goods.getSpecOpen()==1 && goods.getSpecSizeOpen()==0){
            GoodsSku sku = new GoodsSku();
            sku.setSkuId(idGenerator.nextStringId());
            sku.setGoodsId(goods.getGoodsId());
            sku.setSkuNo(idGenerator.nextStringId());
            String skuName = getSkuName(goods,attrValue,null);
            sku.setSkuGoodsName(skuName);
            sku.setSellerId(store.getSellerId());
            sku.setStoreId(store.getStoreId());
            sku.setColorAttrValId(attrValueId);
            sku.setColorAttrValName(attrValue);
            List<ShopAttributeValueSimpleVo> voList = new ArrayList<>();
            ShopAttributeValueSimpleVo colorVo = new ShopAttributeValueSimpleVo();
            BeanUtils.copyProperties(shopAttributeValue,colorVo);
            voList.add(colorVo);
            String attrJson = JSONObject.toJSONString(voList);
            sku.setAttrValueJson(attrJson);
            sku.setCreator(sellerId);
            sku.setCreateTime(new Date());
            goodsSkuService.saveSku(sku);
        }

        return JSONResultUtil.createJsonObject(record);
    }


    /**
     * 商品SKU列表
     * @param goodsId
     * @return
     */
    @ApiOperation("商品SKU列表")
    @GetMapping("/getGoodsSkuList")
    public JSONObject getGoodsSkuList(@ApiParam(required = true) @RequestParam String goodsId) {
        List<GoodsSku> goodsSkuList = goodsSkuService.findSkuByGoodsId(goodsId);
        return JSONResultUtil.createJsonObject(goodsSkuList);
    }

    /**
     * 获取商品属性
     * @param goodsId
     * @return
     */
    @ApiOperation("获取商品属性")
    @GetMapping("/getGoodsAttrs")
    public JSONObject getGoodsAttrs(@ApiParam(required = true) @RequestParam String goodsId){
        JSONObject data = new JSONObject();
        Goods goods = goodsService.getGoodsById(goodsId);
        Assert.notNull(goods,"goodsId is not exists");

        data.put("goods",goods);
        if(goods.getSpecOpen()==1){
            Attribute specAttr = GoodsAttributeUtil.getGoodsColorAttr();

            GoodsAttributeValue condition = new GoodsAttributeValue();
            condition.setGoodsId(goods.getGoodsId());
            condition.setAttrId(specAttr.getAttrId());
            List<GoodsAttributeValue> specAttrValList = goodsAttributeValueService.findGoodsAttributeValueList(condition);
            data.put("specAttrValList",specAttrValList);
        }
        if(goods.getSpecSizeOpen()==1){
            Attribute sizeAttr = GoodsAttributeUtil.getGoodsSizeAttr();
            GoodsAttributeValue condition = new GoodsAttributeValue();
            condition.setGoodsId(goods.getGoodsId());
            condition.setAttrId(sizeAttr.getAttrId());
            List<GoodsAttributeValue> sizeAttrValList = goodsAttributeValueService.findGoodsAttributeValueList(condition);
            data.put("sizeAttrValList",sizeAttrValList);
        }

        return JSONResultUtil.createJsonObject(data);
    }


    @ApiOperation("更新尺码SKU列表")
    @PostMapping("/updateSizeSkuList")
    public JSONObject updateSizeSkuList(@RequestBody @Valid UpdateSizeSkuListParam param, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return JSONResultUtil.createJsonObject(ErrorCodeFeature.VALID_BIND_ERROR,bindingResult.getFieldErrors());
        }
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家未登陆");
        String sellerId = CacheSellerHolderLocal.getSeller().getSellerId();
        Store store =CacheStoreHolderLocal.getStore();
        String goodsId = param.getGoodsId();
        ShopAttributeValue colorAttrVal = shopAttributeValueService.getShopAttributeValueById(param.getColorAttrValId());
        ShopAttributeValueSimpleVo colorSimpleVo = new ShopAttributeValueSimpleVo();
        BeanUtils.copyProperties(colorAttrVal,colorSimpleVo);
        GoodsSku condition = new GoodsSku();
        condition.setGoodsId(goodsId);
        condition.setColorAttrValId(param.getColorAttrValId());
        Goods goods = goodsService.getGoodsById(goodsId);
        List<String> sizeAttrValIds = param.getSizeAttrValIds();
        List<GoodsSku> allWithDelList = goodsSkuService.findSkuByCondition(condition);
        List<GoodsSku> newList = new ArrayList<>();
        List<GoodsSku> updateAddList = new ArrayList<>();
        List<GoodsSku> delList = new ArrayList<>(allWithDelList);
        for (String sizeAttrValId : sizeAttrValIds){
            boolean exists = false;
            for (GoodsSku sku :
                    allWithDelList) {
                if(sku.getSizeAttrValId()!=null && sizeAttrValId.equals(sku.getSizeAttrValId())){
                    exists = true;
                    if(sku.getIsDel()==1){
                        // 校验SkuNo 唯一 不删除状态是否有此SKU
                        GoodsSku tmp = goodsSkuService.findUniqueBySkuNo(sku.getSkuNo());
                        if(tmp!=null){
                            sku.setSkuNo(idGenerator.nextStringId());
                        }
                        sku.setIsDel(BaseEntityInfo.STATE_OK);
                        sku.setModifier(sellerId);
                        sku.setModifyTime(new Date());
                        updateAddList.add(sku);
                    }
                    // 从删除列表移除
                    delList.remove(sku);
                }
            }
            if(!exists){
                ShopAttributeValue sizeAttrVal = shopAttributeValueService.getShopAttributeValueById(sizeAttrValId);
                ShopAttributeValueSimpleVo sizeSimpleVo = new ShopAttributeValueSimpleVo();
                BeanUtils.copyProperties(sizeAttrVal,sizeSimpleVo);
                List<ShopAttributeValueSimpleVo> jsonVoList = new ArrayList<>();
                jsonVoList.add(colorSimpleVo);
                jsonVoList.add(sizeSimpleVo);
                String attrJson = JSONObject.toJSONString(jsonVoList);
                GoodsSku sku = new GoodsSku();
                sku.setSkuId(idGenerator.nextStringId());
                sku.setSkuNo(idGenerator.nextStringId());
                sku.setGoodsId(goodsId);

                String skuName = getSkuName(goods,colorAttrVal.getAttributeValue(),sizeAttrVal.getAttributeValue());
                sku.setSkuGoodsName(skuName);
                sku.setSellerId(store.getSellerId());
                sku.setStoreId(store.getStoreId());
                sku.setAttrValueJson(attrJson);
                sku.setColorAttrValId(colorAttrVal.getAttrValueId());
                sku.setColorAttrValName(colorAttrVal.getAttributeValue());
                sku.setSizeAttrValId(sizeAttrVal.getAttrValueId());
                sku.setSizeAttrValName(sizeAttrVal.getAttributeValue());
                sku.setCreator(sellerId);
                sku.setCreateTime(new Date());
                newList.add(sku);
            }
        }
        for (GoodsSku sku :
                delList) {
            sku.setIsDel(BaseEntityInfo.STATE_DELETE);
            sku.setModifier(sellerId);
            sku.setModifyTime(new Date());
        }

        int count = goodsSkuService.batchCreateAndDelSku(newList,updateAddList,delList);
        // 返回新的SKU列表
        List<GoodsSku> result = goodsSkuService.findSkuByGoodsId(goodsId);

        return JSONResultUtil.createJsonObject(result);
    }



    @ApiOperation("上传商品图片")
    @PostMapping("/uploadProductPicture")
    public JSONObject uploadProductPicture(
            @RequestParam("file") MultipartFile[] files,
            @ApiParam(value = "商品Id") @RequestParam(value = "goodsId",required = false) String goodsId,
            @ApiParam(value = "图片类型 1 封面图 2 主图 3 详情图",defaultValue = "1",example = "1") @RequestParam(value = "type",required = false,defaultValue = "1") Integer type) throws Exception {
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家未登录");
        Store store = CacheStoreHolderLocal.getStore();
        Seller seller = CacheSellerHolderLocal.getSeller();


        String paramGoodsId = goodsId;
        if(StringUtils.isEmpty(goodsId)){
            goodsId = idGenerator.nextStringId();
        }
        List<GoodsUploadPicture> list = new ArrayList<>();
        for (MultipartFile file :
                files) {
            byte[] uploadBytes = file.getBytes();
            String srcMd5 = DigestUtils.md5DigestAsHex(uploadBytes);
            if(StringUtils.isNotEmpty(paramGoodsId)){
                GoodsUploadPicture tmp = goodsUploadPictureService.getByGoodsIdAndSrcMd5(store.getStoreId(),paramGoodsId,srcMd5);
                if(tmp!=null){
                    list.add(tmp);
                    continue;
                }
            }



            String dayDir = DateFormatUtils.format(new Date(),"yyyyMMdd");
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String destFileName = Long.toHexString(System.currentTimeMillis())+"."+extension;

            String destFileName200x200 = destFileName+"_200x200."+extension;

            /**
             * 图片压缩
             * 500x500 banner 主图 详情图
             * 200x200 商品列表图
             * 其他尺寸 oss 压缩
             */
            byte[] bytes800 = zipImageFile(uploadBytes,800,800);
//            byte[] bytes200 = zipImageFile(uploadBytes,200,200);
            InputStream inputStream500 = new ByteArrayInputStream(bytes800);
//            InputStream inputStream200 = new ByteArrayInputStream(bytes200);

            String destFile500 = store.getStoreCode()+"/"+dayDir+"/"+goodsId+"/"+destFileName;
            String destFile200 = store.getStoreCode()+"/"+dayDir+"/"+goodsId+"/"+destFileName200x200;
            String url = ossUploadService.upload(inputStream500,destFile500);
//            String url200 = ossUploadService.upload(inputStream200,destFile200);
            // 保存图片
            GoodsUploadPicture goodsUploadPicture = new GoodsUploadPicture();
            goodsUploadPicture.setPictureId(idGenerator.nextStringId());
            goodsUploadPicture.setGoodsId(goodsId);
            goodsUploadPicture.setStoreId(store.getStoreId());
            goodsUploadPicture.setFileSize(bytes800.length);
            String md5 = DigestUtils.md5DigestAsHex(bytes800);
            goodsUploadPicture.setPicMd5(md5);
            goodsUploadPicture.setSrcMd5(srcMd5);
            goodsUploadPicture.setCreator(seller.getSellerId());
            goodsUploadPicture.setPictureName(file.getOriginalFilename());
            goodsUploadPicture.setPictureUrl(url);
            goodsUploadPicture.setCreateTime(new Date());
            goodsUploadPictureService.saveGoodsUploadPicture(goodsUploadPicture);
            list.add(goodsUploadPicture);
        }

        return JSONResultUtil.createJsonObject(list);
    }

    private byte[] zipImageFile(byte[] bytes,int width,int height) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(inputStream)
                .size(width,height)
                .toOutputStream(outputStream);
        return outputStream.toByteArray();
    }


    @ApiOperation("校验满足申请上架条件")
    @GetMapping("/validateGoodsShow")
    public JSONObject validateGoodsShow(@ApiParam(required = true) @RequestParam String goodsId){
        String errors = validGoodsShow(goodsId);
        boolean valid = StringUtils.isEmpty(errors);
        JSONObject data = new JSONObject();
        data.put("valid",valid);
        data.put("errors",errors);

        return JSONResultUtil.createJsonObject(data);
    }

    /**
     * 校验是否符合申请上架条件
     * @param goodsId
     * @return
     */
    private String validGoodsShow(String goodsId){
        String errors = "";
        Goods goods = goodsService.getGoodsById(goodsId);
        if(goods.getGoodsStatus()!=1){
            return "商品不是待发布状态";
        }
        List<GoodsSku> skuList = goodsSkuService.findSkuByGoodsId(goodsId);
        if(skuList.isEmpty()){
            errors="sku为空";
        }
        for (GoodsSku sku :
                skuList) {
            if(StringUtils.isEmpty(sku.getSpecFacePictures())){
                errors +=sku.getSkuNo()+"封面图为空 ";
            }
            if(StringUtils.isEmpty(sku.getPictureMain())){
                errors +=sku.getSkuNo()+"主图为空 ";
            }
        }

        return errors;
    }


    @ApiOperation("申请上架")
    @GetMapping("/applyForGoodsShow")
    public JSONObject applyForGoodsShow(@ApiParam(required = true) @RequestParam String goodsId){
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家未登录");
        Goods goods = goodsService.getGoodsById(goodsId);
        if(goods.getGoodsStatus()!=1){
            return JSONResultUtil.createJsonObject("商品不是待发布状态");
        }
        String errors = validGoodsShow(goodsId);
        if(StringUtils.isNotEmpty(errors)){
            return JSONResultUtil.createJsonObject(errors);
        }
        int nextGoodsStatus = shopConfigureProperties.isGoodsAuditEnable()? GoodsInfo.STATUS_AUDIT_WAIT:GoodsInfo.STATUS_SHOW_WAIT;
        goods.setGoodsStatus(nextGoodsStatus);
        goods.setModifier(CacheSellerHolderLocal.getSeller().getSellerId());
        goods.setModifyTime(new Date());
        int count = goodsService.updateGoodsById(goods);

        return JSONResultUtil.createJsonObject(goods);
    }


    /**
     *  商品改为可编辑状态 待发布状态
     * @param parameter
     * @return
     */
    @ApiOperation("更改商品状态")
    @PostMapping("/updateGoodsStatus")
    public JSONObject updateGoodsStatus(@RequestBody @Valid UpdateGoodsStatusParameter parameter, BindingResult bindingResult){
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家未登录");
        if(bindingResult.hasErrors()){
            return JSONResultUtil.createJsonObject(bindingResult.getFieldErrors());
        }
        String goodsId = parameter.getGoodsId();
        Goods goods = goodsService.getGoodsById(goodsId);
        boolean valid = validSellerGoodsStatusEnable(goods.getGoodsStatus(),parameter.getGoodsStatus());
        if(!valid){
            String msg = "状态由"+goods.getGoodsStatus()+"到"+parameter.getGoodsStatus();
            return JSONResultUtil.createJsonObject(msg);
        }

        goods.setGoodsId(goodsId);
        goods.setGoodsStatus(parameter.getGoodsStatus());
        goods.setModifier(CacheSellerHolderLocal.getSeller().getSellerId());
        goods.setModifyTime(new Date());
        int count = goodsService.updateGoodsById(goods);

        JSONObject data = new JSONObject();
        data.put("count",count);
        data.put("goods",data);

        return JSONResultUtil.createJsonObject(goods);
    }

    /**
     * 批量操作 上架 下架
     * @param parameter
     * @param bindingResult
     * @return
     */
    @PostMapping("/batchUpdateGoodsStatus")
    public JSONObject batchUpdateGoodsStatus(@RequestBody @Valid BatchUpdateGoodsStatusParameter parameter, BindingResult bindingResult){
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家未登录");
        if(bindingResult.hasErrors()){
            return JSONResultUtil.createJsonObject(bindingResult.getFieldErrors());
        }
        String[] goodsIds = parameter.getGoodsIds();
        String msg = "";
        int count =0;
        for (String goodsId :
                goodsIds) {
            Goods goods = goodsService.getGoodsById(goodsId);
            boolean valid = validSellerGoodsStatusEnable(goods.getGoodsStatus(),parameter.getGoodsStatus());
            if(!valid){
                msg += goods.getGoodsSn()+" ";
            }
            goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setGoodsStatus(parameter.getGoodsStatus());
            goods.setModifier(CacheSellerHolderLocal.getSeller().getSellerId());
            goods.setModifyTime(new Date());
            count += goodsService.updateGoodsById(goods);
        }
        String successMessage = String.format("一共:%d 成功:%d",goodsIds.length,count);
        JSONObject jsonObject = JSONResultUtil.createJsonObject(new ResultState(MESSAGE_TYPE_SUCCESS,successMessage));

        return jsonObject;
    }


    /**
     * 批量删除商品
     * @param parameter
     * @param bindingResult
     * @return
     */
    @PostMapping("/batchDeleteGoods")
    public JSONObject batchDeleteGoods(@RequestBody @Valid BatchDeleteGoodsParameter parameter,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JSONResultUtil.createJsonObject(bindingResult.getFieldErrors());
        }
        String[] goodsIds = parameter.getGoodsIds();
        int count =0;
        String sellerId = CacheSellerHolderLocal.getSeller().getSellerId();
        for (String goodsId:goodsIds){
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setIsDel(BaseEntityInfo.STATE_DELETE);
            goods.setModifier(sellerId);
            goods.setModifyTime(new Date());
            count += goodsService.deleteGoods(goods);
        }

        return JSONResultUtil.createJsonObject(count);
    }

    /**
     * 校验货号唯一 goodsSn
     * @param goodsSn
     * @return
     */
    @GetMapping("/validGoodsSn")
    public JSONObject validGoodsSnUnique(@ApiParam(required = true) @RequestParam String goodsSn){
        Goods goods = goodsService.getGoodsBySn(goodsSn);
        boolean flag = goods==null;
        return JSONResultUtil.createJsonObject(flag);
    }

    /**
     * 校验商品可改变的状态
     * @param currentStatus
     * @param changeStatus
     * @return
     */
    private boolean validSellerGoodsStatusEnable(int currentStatus, int changeStatus){
        if(currentStatus==changeStatus){
            return false;
        }
        //    商品状态,1: 未发布，2：待审核，3：审核驳回，4：待上架，5：在售，6：已下架，7：锁定， 8： 申请解锁
        boolean valid = false;
        switch (currentStatus){
            case 1:{
                valid = changeStatus==2;
                break;
            }case 2:{
                valid = changeStatus==1;
                break;
            }case 3:{
                valid = changeStatus==1;
                break;
            }case 4:{
                valid = changeStatus==5 || changeStatus==1;
                break;
            }case 5:{
                valid = changeStatus==6;
                break;
            }case 6:{
                valid = changeStatus==1 || changeStatus==5;
                break;
            }case 7:{
                valid = changeStatus==8;
                break;
            }case 8:{
                valid = false;
                break;
            }default:{
                valid = false;
            }
        }
        return valid;
    }


    @ApiOperation("删除商品规格属性")
    @PostMapping("/deleteGoodsSpecAttr")
    public ResultObject deleteGoodsSpecAttr(@RequestBody @Valid DeleteGoodsSpecAttrParameter parameter, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResultObject<>(ErrorCode.REQUEST_PARAM_ERROR,bindingResult.getFieldErrors());
        }
        String goodsId = parameter.getGoodsId();
        String tbId = parameter.getTbId();
        GoodsAttributeValue goodsAttributeValue = goodsAttributeValueService.getGoodsAttributeValueById(tbId);
        Assert.notNull(goodsAttributeValue,"goodsAttributeValue is not exist tbId: "+tbId);
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setGoodsId(goodsId);
        if(CommonDictionary.GOODS_SPEC_COLOR_ATTR_ID.equals(goodsAttributeValue.getAttrId())){
            goodsSku.setColorAttrValId(goodsAttributeValue.getValueId());
        }
        if(CommonDictionary.GOODS_SPEC_SIZE_ATTR_ID.equals(goodsAttributeValue.getAttrId())){
            goodsSku.setSizeAttrValId(goodsAttributeValue.getValueId());
        }
        List<GoodsSku> goodsSkuList = goodsSkuService.findSkuByCondition(goodsSku);
        String modifier = CacheSellerHolderLocal.getSeller().getSellerName();
        goodsAttributeValue.setModifier(modifier);
        goodsAttributeValue.setModifyTime(new Date());
        ResultObject resultObject = goodsAttributeValueService.deleteSpecAttrValue(goodsAttributeValue, goodsSkuList);
        return resultObject;
    }
}
