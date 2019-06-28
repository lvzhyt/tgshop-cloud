package com.tg.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.ResultState;
import com.tg.shop.core.domain.auth.entity.Store;
import com.tg.shop.core.domain.categories.entity.Attribute;
import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.product.entity.GoodsAttributeValue;
import com.tg.shop.core.domain.product.entity.GoodsSku;
import com.tg.shop.core.domain.product.entity.ShopAttributeValue;
import com.tg.shop.core.domain.product.vo.ShopAttributeValueSimpleVo;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.domain.util.PageResult;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.*;
import com.tg.shop.core.validate.InsertValid;
import com.tg.shop.product.request.param.AddGoodsSpecAttrParam;
import com.tg.shop.product.service.GoodsAttributeValueService;
import com.tg.shop.product.service.GoodsService;
import com.tg.shop.product.service.GoodsSkuService;
import com.tg.shop.product.service.ShopAttributeValueService;
import com.tg.shop.product.utils.GoodsAttributeUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/getGoodsById")
    public ResultObject<Goods> getGoodsById(String goodsId){
        Goods goods =  goodsService.getGoodsById(goodsId);
        return new ResultObject<>(goods);
    }

    @GetMapping("/getGoodsBySn")
    public ResultObject<Goods> getGoodsBySn(@RequestParam String goodsSn){
        Goods goods =  goodsService.getGoodsBySn(goodsSn);
        return new ResultObject<>(goods);
    }

    @ApiOperation(value = "findSellerGoodsPageList",tags = "商家商品列表")
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
        PageInfo<Goods> page =  goodsService.findGoodsPageList(pageCondition);
        return new ResultObject<>(new PageResult<>(page));
    }

    @ApiOperation(value = "findGoodsPageList",tags = "商品列表")
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
    @ApiOperation(value = "",tags = "添加商品规格")
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
    @ApiOperation(value = "getGoodsSkuList",tags = "商品SKU列表")
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
    @GetMapping("/goods/goodsAttrs")
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

}
