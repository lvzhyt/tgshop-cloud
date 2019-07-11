package com.tg.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.ResultState;
import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.domain.product.entity.*;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuInventoryResultVo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuPriceResultVo;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.CacheSellerHolderLocal;
import com.tg.shop.core.utils.ErrorCodeFeature;
import com.tg.shop.core.utils.JSONResultUtil;
import com.tg.shop.product.request.param.UpdateGoodsSkuInventoryParameter;
import com.tg.shop.product.request.param.UpdateGoodsSkuPicParameter;
import com.tg.shop.product.request.param.UpdateGoodsSkuPriceParameter;
import com.tg.shop.product.request.param.UpdateSkuStatusParameter;
import com.tg.shop.product.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sku")
public class GoodsSkuController {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private GoodsSkuService goodsSkuService;
    @Autowired
    private GoodsUploadPictureService goodsUploadPictureService;
    @Autowired
    private GoodsSkuPictureService goodsSkuPictureService;
    @Autowired
    private GoodsSkuPriceService goodsSkuPriceService;
    @Autowired
    private GoodsSkuInventoryService goodsSkuInventoryService;

    @ApiOperation("商品SKU列表")
    @GetMapping("/listSkuByGoodsId")
    public ResultObject<List<GoodsSku>> listSkuByGoodsId(@RequestParam(required = true) String goodsId){
       List<GoodsSku> list = goodsSkuService.findSkuByGoodsId(goodsId);
       return new ResultObject<>(list);
    }


    @ApiOperation("更新sku图片")
    @PostMapping("/updateGoodsSkuPic")
    public JSONObject updateGoodsSkuPic(@RequestBody @Valid UpdateGoodsSkuPicParameter param, BindingResult bindingResult){
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家未登录");
        if(bindingResult.hasErrors()){
            return JSONResultUtil.createJsonObject(ErrorCodeFeature.VALID_BIND_ERROR,bindingResult.getFieldErrors());
        }
        String goodsId = param.getGoodsId();
        String[] skuIds = param.getSkuIds();
        String[] picIds = param.getPicIds();
        Integer type = param.getType();
        Seller seller = CacheSellerHolderLocal.getSeller();

        StringBuilder stringBuilder = new StringBuilder();
        List<GoodsSkuPicture> skuPictureList = new ArrayList<>();
        for (String id :
                picIds) {
            GoodsUploadPicture picture = goodsUploadPictureService.getPictureById(id);
            stringBuilder.append(picture.getPictureUrl());
            stringBuilder.append(",");
        }
        String picUrls = stringBuilder.toString();
        if(picUrls.endsWith(",")){
            picUrls = picUrls.substring(0,picUrls.length()-1);
        }
        for (String skuId :
                skuIds) {
            GoodsSku sku = new GoodsSku();
            sku.setSkuId(skuId);
            GoodsSkuPicture condition = new GoodsSkuPicture();
            condition.setSkuId(skuId);
            condition.setPictureType(type);
            // 删除 根据skuId  pictureType
            int delCount = goodsSkuPictureService.shiftDeleteBySkuIdAndType(skuId,type);
            for (int i = 0; i < picIds.length; i++) {
                String picId = picIds[i];
                GoodsUploadPicture uploadPicture = goodsUploadPictureService.getPictureById(picId);
                GoodsSkuPicture goodsSkuPicture = createGoodsSkuPictures(seller.getSellerId(),skuId,goodsId,picId,uploadPicture.getPictureUrl(),type,i);
                // 新增sku-picture 关系表
                goodsSkuPictureService.saveSkuPicture(goodsSkuPicture);
                skuPictureList.add(goodsSkuPicture);
            }
            switch (type){
                case 1:{
                    sku.setSpecFacePictures(picUrls);
                    break;
                }case 2:{
                    sku.setPictureMain(picUrls);
                    break;
                }case 3:{
                    sku.setPictureDescription(picUrls);
                    break;
                }default:{

                }
            }
            sku.setModifier(seller.getSellerId());
            sku.setModifyTime(new Date());
            goodsSkuService.updateSkuById(sku);

        }

        List<GoodsSku> list = goodsSkuService.findSkuByGoodsId(param.getGoodsId());

        return JSONResultUtil.createJsonObject(list);
    }

    private GoodsSkuPicture createGoodsSkuPictures(String creator,String skuId,String goodsId,
                                                   String pictureId,String pictureUrl,int type,int sortNum){
        GoodsSkuPicture goodsSkuPicture = new GoodsSkuPicture();
        goodsSkuPicture.setTbId(idGenerator.nextStringId());
        goodsSkuPicture.setSkuId(skuId);
        goodsSkuPicture.setGoodsId(goodsId);
        goodsSkuPicture.setPictureId(pictureId);
        goodsSkuPicture.setPictureDescription(pictureUrl);
        goodsSkuPicture.setPictureType(type);
        goodsSkuPicture.setSortNum(sortNum);
        goodsSkuPicture.setCreateTime(new Date());
        goodsSkuPicture.setCreator(creator);
        return goodsSkuPicture;
    }

    @ApiOperation("sku价格列表")
    @GetMapping("/listSkuPriceByGoodsId")
    public JSONObject listSkuPriceByGoodsId(@ApiParam(required = true) @RequestParam String goodsId){
        List<GoodsSkuPriceResultVo> list = goodsSkuService.findSkuPriceListByGoodsId(goodsId);
        return JSONResultUtil.createJsonObject(list);
    }


    @ApiOperation("更新价格")
    @PostMapping("/updateGoodsSkuPrice")
    public JSONObject updateGoodsSkuPrice(@RequestBody @Valid UpdateGoodsSkuPriceParameter parameter, BindingResult bindingResult){
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家未登录");
        if(bindingResult.hasErrors()){
            return JSONResultUtil.createJsonObject(ErrorCodeFeature.VALID_BIND_ERROR,bindingResult.getFieldErrors());
        }
        String sellerId = CacheSellerHolderLocal.getSeller().getSellerId();
        String sellerName = CacheSellerHolderLocal.getSeller().getSellerName();
        String[] skuIds = parameter.getSkuIds();
        int count = 0;
        for (String skuId :
                skuIds) {
            GoodsSkuPrice skuPrice = goodsSkuPriceService.getBySkuId(skuId);
            if(!checkPriceChange(skuPrice,parameter)){
                continue;
            }
            GoodsSkuPriceLog priceLog = new GoodsSkuPriceLog();
            BeanUtils.copyProperties(skuPrice,priceLog);
            priceLog.setTbId(idGenerator.nextStringId());
            priceLog.setPriceId(skuId);

            if(parameter.getCostPrice()!=null){
                skuPrice.setCostPrice(parameter.getCostPrice());
                priceLog.setCostPrice(parameter.getCostPrice());
            }
            if(parameter.getMarketPrice()!=null){
                skuPrice.setMarketPrice(parameter.getMarketPrice());
                priceLog.setMarketPrice(parameter.getMarketPrice());
            }
            if(parameter.getSalePrice()!=null){
                skuPrice.setSalePrice(parameter.getSalePrice());
                priceLog.setSalePrice(parameter.getSalePrice());
            }
            if(parameter.getPlusPriceOpen()!=null){
                skuPrice.setPlusPriceOpen(parameter.getPlusPriceOpen());
                priceLog.setPlusPriceOpen(parameter.getPlusPriceOpen());
            }
            if(parameter.getPlusPrice()!=null){
                skuPrice.setPlusPrice(parameter.getPlusPrice());
                priceLog.setPlusPrice(parameter.getPlusPrice());
            }
            if(parameter.getSuperVipPriceOpen()!=null){
                skuPrice.setSuperVipPriceOpen(parameter.getSuperVipPriceOpen());
                priceLog.setSuperVipPriceOpen(parameter.getSuperVipPriceOpen());
            }
            if(parameter.getSuperVipPrice()!=null){
                skuPrice.setSuperVipPrice(parameter.getSuperVipPrice());
                priceLog.setSuperVipPrice(parameter.getSuperVipPrice());
            }
            String remark = "modify by "+sellerName;
            skuPrice.setRemark(remark);
            priceLog.setRemark(remark);
            skuPrice.setModifier(sellerId);
            Date date = new Date();
            skuPrice.setModifyTime(date);
            priceLog.setCreator(sellerId);
            priceLog.setCreateTime(date);
            priceLog.setModifier(null);
            priceLog.setModifyTime(null);
            count += goodsSkuPriceService.updateSkuPrice(skuPrice,priceLog);
        }
        JSONObject resultCount = new JSONObject();
        resultCount.put("count",count);
        return JSONResultUtil.createJsonObject(resultCount);
    }

    /**
     * 检查价格是否改变
     * @param skuPrice
     * @param parameter
     * @return
     */
    private boolean checkPriceChange(GoodsSkuPrice skuPrice, UpdateGoodsSkuPriceParameter parameter){
        if(parameter.getCostPrice()!=null){
            boolean changed = skuPrice.getCostPrice()==null || parameter.getCostPrice().setScale(2).compareTo(skuPrice.getCostPrice().setScale(2))!=0;
            if(changed){
                return true;
            }
        }
        if(parameter.getMarketPrice()!=null){
            boolean changed = skuPrice.getMarketPrice()==null || parameter.getMarketPrice().setScale(2).compareTo(skuPrice.getMarketPrice().setScale(2))!=0;
            if(changed){
                return true;
            }
        }
        if(parameter.getSalePrice()!=null){
            boolean changed = skuPrice.getSalePrice()==null || parameter.getSalePrice().setScale(2).compareTo(skuPrice.getSalePrice().setScale(2))!=0;
            if(changed){
                return true;
            }
        }
        if(parameter.getPlusPriceOpen()!=null){
            boolean changed = skuPrice.getPlusPriceOpen()==null || !parameter.getPlusPriceOpen().equals(skuPrice.getPlusPriceOpen());
            if(changed){
                return true;
            }
        }
        if(parameter.getPlusPrice()!=null){
            boolean changed = skuPrice.getPlusPrice()==null || parameter.getPlusPrice().setScale(2).compareTo(skuPrice.getPlusPrice().setScale(2))!=0;
            if(changed){
                return true;
            }
        }
        if(parameter.getSuperVipPriceOpen()!=null){
            boolean changed = skuPrice.getSuperVipPriceOpen()==null || (int)parameter.getSuperVipPriceOpen()!=skuPrice.getSuperVipPriceOpen();
            if(changed){
                return true;
            }
        }
        if(parameter.getSuperVipPrice()!=null){
            boolean changed = skuPrice.getSuperVipPrice()==null || parameter.getSuperVipPrice().setScale(2).compareTo(skuPrice.getSuperVipPrice().setScale(2))!=0;
            if(changed){
                return true;
            }
        }

        return false;
    }


    @ApiOperation("库存查询")
    @GetMapping("/getSkuInventoryListByGoodsId")
    public JSONObject getSkuInventoryListByGoodsId(@ApiParam(required = true) @RequestParam String goodsId){
        List<GoodsSkuInventoryResultVo> list = goodsSkuService.findSkuInventoryListByGoodsId(goodsId);
        return JSONResultUtil.createJsonObject(list);
    }


    @ApiOperation("更新库存")
    @PostMapping("/updateGoodsSkuInventory")
    public JSONObject updateGoodsSkuInventory(@RequestBody @Valid UpdateGoodsSkuInventoryParameter parameter, BindingResult bindingResult){
        Assert.notNull(CacheSellerHolderLocal.getSeller(),"商家未登录");
        if(bindingResult.hasErrors()){
            return JSONResultUtil.createJsonObject(ErrorCodeFeature.VALID_BIND_ERROR,bindingResult.getFieldErrors());
        }
        String sellerId = CacheSellerHolderLocal.getSeller().getSellerId();
        String sellerName = CacheSellerHolderLocal.getSeller().getSellerName();
        String[] skuIds = parameter.getSkuIds();
        int inventoryNum = parameter.getInventory();
        int count = 0;
        for (String skuId:
                skuIds) {
            GoodsSkuInventory record = goodsSkuInventoryService.getBySkuId(skuId);
            record.setInventoryNum(inventoryNum);
            int leftNum  = inventoryNum-record.getLockNum();
            if(leftNum<0){
                throw new RuntimeException("设定库存<锁定库存.SkuId: "+skuId);
            }
            record.setLeftNum(leftNum);
            record.setModifier(sellerId);
            record.setModifyTime(new Date());
            record.setVersion(record.getVersion());
            String remark = "modify by "+sellerName+" old val "+record.getInventoryNum();
            record.setRemark(remark);
            int ct = goodsSkuInventoryService.updateSkuInventoryByVersion(record);
            count +=ct;
        }

        return JSONResultUtil.createJsonObject(new ResultState(count));
    }

    /**
     *  获取商品sku详情列表
     * @param goodsId
     * @return
     */
    @GetMapping("/getSkuDetailListByGoodsId")
    public JSONObject getSkuDetailListByGoodsId(@ApiParam(required = true) @RequestParam String goodsId){
        List<GoodsSkuDetailResultVo> list = goodsSkuService.findSkuDetailListByGoodsId(goodsId);

        return JSONResultUtil.createJsonObject(list);
    }



    /**
     * 更新SKU状态
     * @param parameter
     * @param bindingResult
     * @return
     */
    @ApiOperation("开启关闭SKU")
    @PostMapping("/updateSkuStatus")
    public ResultObject updateSkuStatus(@Valid @RequestBody UpdateSkuStatusParameter parameter, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResultObject<>(ErrorCode.REQUEST_PARAM_ERROR, bindingResult.getFieldErrors());
        }
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setSkuId(parameter.getSkuId());
        goodsSku.setSkuStatus(parameter.getSkuStatus());
        goodsSku.setModifyTime(new Date());
        goodsSku.setModifier(CacheSellerHolderLocal.getSeller().getSellerId());
        int count = goodsSkuService.updateSkuById(goodsSku);

        return new ResultObject<>(count);
    }
}
