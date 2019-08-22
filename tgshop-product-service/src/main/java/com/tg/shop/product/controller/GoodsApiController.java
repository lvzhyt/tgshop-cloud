package com.tg.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.product.entity.*;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.utils.CommonDictionary;
import com.tg.shop.product.result.vo.GoodsSkuSpecDetailVo;
import com.tg.shop.product.result.vo.SkuSpecItemVo;
import com.tg.shop.product.result.vo.SpecAttrVo;
import com.tg.shop.product.result.vo.SpecKeyValuesVo;
import com.tg.shop.product.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class GoodsApiController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsSkuService goodsSkuService;
    @Autowired
    private ShopAttributeValueService shopAttributeValueService;
    @Autowired
    private GoodsAttributeService goodsAttributeService;
    @Autowired
    private GoodsAttributeValueService goodsAttributeValueService;


    /**
     * 获取选择商品SKU信息
     * @param skuId
     * @return
     */
    @ApiOperation("获取选择商品SKU信息")
    @GetMapping("/sku/getChooseSku")
    public ResultObject getChooseSku(@RequestParam String skuId){
        GoodsSku goodsSku = goodsSkuService.getBySkuId(skuId);
        JSONObject jsonObject = new JSONObject();
        GoodsSkuSpecDetailVo goodsSkuSpecDetailVo = new GoodsSkuSpecDetailVo();
        List<SpecKeyValuesVo> treeList = new ArrayList<>();
        String goodsId = goodsSku.getGoodsId();
        Goods goods = goodsService.getGoodsById(goodsId);
        goodsSkuSpecDetailVo.setNone_sku(goods.getSpecOpen()==0);

        // 颜色规格
        if(goods.getSpecOpen()==1){
            String colorAttrId = CommonDictionary.GOODS_SPEC_COLOR_ATTR_ID;
            SpecKeyValuesVo specKeyValuesVo = new SpecKeyValuesVo();
            specKeyValuesVo.setK("颜色");
            specKeyValuesVo.setK_s("s1");
            List<SpecAttrVo> specAttrVoList = buildSpecAttrVo(goodsId, colorAttrId);
            specKeyValuesVo.setV(specAttrVoList);
            treeList.add(specKeyValuesVo);
        }
        // 尺码规格
        if(goods.getSpecOpen()==1 && goods.getSpecSizeOpen()==1){
            String sizeAttrId = CommonDictionary.GOODS_SPEC_SIZE_ATTR_ID;
            SpecKeyValuesVo specKeyValuesVo = new SpecKeyValuesVo();
            specKeyValuesVo.setK("尺码");
            specKeyValuesVo.setK_s("s2");
            List<SpecAttrVo> specAttrVoList = buildSpecAttrVo(goodsId, sizeAttrId);
            specKeyValuesVo.setV(specAttrVoList);
            treeList.add(specKeyValuesVo);
        }

        // 所有sku规格类目与其值的从属关系，比如商品有颜色和尺码两大类规格，颜色下面又有红色和蓝色两个规格值
        goodsSkuSpecDetailVo.setTree(treeList);

        List<GoodsSkuDetailResultVo> goodsSkuDetailResultVoList = goodsSkuService.findSkuDetailListByGoodsId(goodsId);
        List<SkuSpecItemVo> skuSpecItemVoList = new ArrayList<>();
        for (GoodsSkuDetailResultVo goodsSkuDetailResultVo : goodsSkuDetailResultVoList) {
            String id = goodsSkuDetailResultVo.getSkuId();
            int price = (int) (goodsSkuDetailResultVo.getSalePrice().doubleValue()*100);
            String s1 = goodsSkuDetailResultVo.getColorAttrValId()!=null?goodsSkuDetailResultVo.getColorAttrValId():"0";
            String s2 = goodsSkuDetailResultVo.getSizeAttrValId()!=null?goodsSkuDetailResultVo.getSizeAttrValId():"0";
            String s3 = "0";
            int stockNum = goodsSkuDetailResultVo.getInventoryNum();
            SkuSpecItemVo skuSpecItemVo = new SkuSpecItemVo(id,price,s1,s2,s3,stockNum);
            skuSpecItemVoList.add(skuSpecItemVo);
        }
        // 所有 sku 的组合列表，比如红色、M 码为一个 sku 组合，红色、S 码为另一个组合
        goodsSkuSpecDetailVo.setList(skuSpecItemVoList);
        goodsSkuSpecDetailVo.setHide_stock(false);

        JSONObject goodsJsonObject = new JSONObject();
        if(!goodsSkuDetailResultVoList.isEmpty()){
            GoodsSkuDetailResultVo goodsSkuDetailResultVoDefault = goodsSkuDetailResultVoList.get(0);
            goodsSkuSpecDetailVo.setPrice(goodsSkuDetailResultVoDefault.getSalePrice());
            goodsSkuSpecDetailVo.setStock_num(goodsSkuDetailResultVoDefault.getInventoryNum());
            goodsSkuSpecDetailVo.setCollection_id(goodsSkuDetailResultVoDefault.getSkuId());
            goodsJsonObject.put("title",goodsSkuDetailResultVoDefault.getSkuGoodsName());
            goodsJsonObject.put("picture",goodsSkuDetailResultVoDefault.getSpecFacePictures());
            goodsJsonObject.put("price",goodsSkuDetailResultVoDefault.getSalePrice());
        }

        jsonObject.put("sku",goodsSkuSpecDetailVo);
        jsonObject.put("goods_info",goodsJsonObject);
        jsonObject.put("goods_id",goodsSku.getGoodsId());
        jsonObject.put("quota",0);
        jsonObject.put("quota_used",0);


        return new ResultObject<>(jsonObject);
    }

    /**
     * 构建商品SKU 属性
     * @param goodsId
     * @param sizeAttrId
     * @return
     */
    private List<SpecAttrVo> buildSpecAttrVo(String goodsId, String sizeAttrId) {
        GoodsAttributeValue goodsAttributeValueCondition = new GoodsAttributeValue();
        goodsAttributeValueCondition.setGoodsId(goodsId);
        goodsAttributeValueCondition.setAttrId(sizeAttrId);
        List<GoodsAttributeValue> goodsAttributeValueServiceList = goodsAttributeValueService.findGoodsAttributeValueList(goodsAttributeValueCondition);
        List<SpecAttrVo> specAttrVoList = new ArrayList<>();
        for (GoodsAttributeValue goodsAttributeValue : goodsAttributeValueServiceList) {
            String id = goodsAttributeValue.getValueId();
            String name = goodsAttributeValue.getAttrValueName();
            String imgUrl = goodsAttributeValue.getImgUrl();
            SpecAttrVo specAttrVo = new SpecAttrVo(id, name, imgUrl);
            specAttrVoList.add(specAttrVo);
        }
        return specAttrVoList;
    }

}
