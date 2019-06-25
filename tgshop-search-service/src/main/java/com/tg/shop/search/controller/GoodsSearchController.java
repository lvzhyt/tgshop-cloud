package com.tg.shop.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.entity.EsGoods;
import com.tg.shop.search.feign.service.FeignGoodsService;
import com.tg.shop.search.feign.service.FeignGoodsSkuService;
import com.tg.shop.search.repositry.GoodsRepository;
import com.tg.shop.search.service.GoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/es")
public class GoodsSearchController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    private FeignGoodsService goodsService;
    @Resource
    private FeignGoodsSkuService goodsSkuService;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private GoodsSearchService goodsSearchService;


    public ResultObject save(){
        EsGoods goods = new EsGoods();
        goodsRepository.save(goods);
        return ResultObject.getInstance();
    }

    @PutMapping("/createGoodsIndex")
    public JSONObject createGoodsIndex(){
        JSONObject jsonObject = new JSONObject();

        boolean success = elasticsearchTemplate.createIndex(EsGoods.class);
        System.out.println("createGoodsIndex:"+success);
        return  jsonObject;
    }

    @GetMapping("/indexGoodsData")
    public JSONObject indexGoodsData(){

        PageCondition<Goods> pageCondition = new PageCondition<>();
        pageCondition.setPageNum(1);
        pageCondition.setPageSize(50);
        pageCondition.setCondition(new Goods());
        PageInfo<Goods> goodsPageInfo = goodsService.findGoodsPageList(pageCondition);
        int pageCount = goodsPageInfo.getPages();
        for (int i = 0; i < pageCount; i++) {
            if(i>0){
                pageCondition.setPageNum(i+1);
                goodsPageInfo = goodsService.findGoodsPageList(pageCondition);
            }
            List<Goods> goodsList = goodsPageInfo.getList();
            for (int j = 0; j < goodsList.size(); j++) {
                Goods goods = goodsList.get(j);
                goodsSearchService.updateGoodsIndex(goods.getGoodsId());
            }
        }
        return new JSONObject();
    }

    @GetMapping("/searchGoods")
    public ResultObject searchGoods(@RequestParam("search") String search,
                                  @RequestParam(required = false,defaultValue = "1") int pageNum,
                                  @RequestParam(required = false,defaultValue = "10")int pageSize){
        Page<EsGoods> page = goodsSearchService.searchGoods(search,pageNum,pageSize);
        return ResultObject.getInstance(page);
    }

}
