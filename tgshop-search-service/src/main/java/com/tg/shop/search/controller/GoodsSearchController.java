package com.tg.shop.search.controller;

import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.entity.EsGoods;
import com.tg.shop.search.entity.vo.SearchGoodsListResultVo;
import com.tg.shop.search.entity.vo.SearchListGoodsItemVo;
import com.tg.shop.search.entity.vo.SkuDetailResultVo;
import com.tg.shop.search.service.GoodsSearchService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class GoodsSearchController {


    @Autowired
    private GoodsSearchService goodsSearchService;


    /**
     * 商品搜索
     * @param search
     * @param pageNum
     * @param pageSize
     * @param sortType
     * @return
     */
    @ApiOperation("商品搜索")
    @GetMapping("/es/searchGoods")
    public ResultObject<SearchGoodsListResultVo> searchGoods(@RequestParam("search") String search,
                                  @RequestParam(required = false,defaultValue = "1") int pageNum,
                                  @RequestParam(required = false,defaultValue = "10")int pageSize,
                                  @RequestParam(name = "sortField",required = false, defaultValue = "0") Integer sortType,
                                  @RequestParam(name = "longitude",required = false) Double longitude,
                                  @RequestParam(name = "latitude",required = false) Double latitude){
        GeoPoint location = null;
        final double offset = 1000;
        if(longitude!=null && latitude!=null){
            location = new GeoPoint(latitude,longitude);
        }
        Page<EsGoods> page = goodsSearchService.searchGoods(search,location,offset,sortType,pageNum,pageSize);
        SearchGoodsListResultVo resultVo = new SearchGoodsListResultVo();
        resultVo.setPageNum(page.getPageable().getPageNumber());
        resultVo.setPageSize(page.getPageable().getPageSize());
        resultVo.setTotalElements(page.getTotalElements());
        resultVo.setTotalPages(page.getTotalPages());
        List<EsGoods> goodsList = page.getContent();
        List<SearchListGoodsItemVo> voList = new ArrayList<>();
        for (EsGoods goods :
                goodsList) {
            SearchListGoodsItemVo vo = new SearchListGoodsItemVo();
            BeanUtils.copyProperties(goods,vo);
            voList.add(vo);
        }
        resultVo.setData(voList);

        return new ResultObject<>(resultVo);
    }

    /**
     * 获取SKU 详情
     * @param skuId
     * @return
     */
    @ApiOperation("获取SKU 详情")
    @GetMapping("/es/getSkuById")
    public ResultObject getSkuById(@RequestParam String skuId){
        EsGoods esGoods = goodsSearchService.findSkuById(skuId);
        SkuDetailResultVo vo = new SkuDetailResultVo();
        BeanUtils.copyProperties(esGoods,vo);
        if(StringUtils.isNotEmpty(esGoods.getPictureMain())){
            String[] arr = esGoods.getPictureMain().split(",");
            for (String tmp : arr) {
                if(StringUtils.isNotBlank(tmp)){
                    vo.getPictureMainList().add(tmp);
                }
            }
        }
        if(StringUtils.isNotEmpty(esGoods.getPictureDescription())){
            String[] arr = esGoods.getPictureDescription().split(",");
            for (String tmp : arr) {
                if(StringUtils.isNotBlank(tmp)){
                    vo.getPictureDescriptionList().add(tmp);
                }
            }
        }
        if(StringUtils.isNotEmpty(esGoods.getTags())){
            String[] arr = esGoods.getTags().split(",");
            for (String tmp : arr) {
                if(StringUtils.isNotBlank(tmp)){
                    vo.getTagList().add(tmp);
                }
            }
        }

        return new ResultObject<>(vo);
    }

    /**
     * 更新商品索引
     * @param goodsId
     * @return
     */
    @ApiOperation("更新商品索引")
    @RequestMapping(value = "/es/updateGoodsSearchIndex",method = RequestMethod.GET)
    public ResultObject updateGoodsSearchIndex(@RequestParam(value = "goodsId") String goodsId){
        ResultObject resultObject = goodsSearchService.updateGoodsIndex(goodsId);
        return resultObject;
    }

}
