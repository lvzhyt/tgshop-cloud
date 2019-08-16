package com.tg.shop.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.entity.EsGoods;
import com.tg.shop.search.feign.service.FeignProductService;
import com.tg.shop.search.repositry.GoodsRepository;
import com.tg.shop.search.service.GoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/es")
public class SearchInitController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    private FeignProductService feignProductService;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private GoodsSearchService goodsSearchService;


    public ResultObject save(){
        EsGoods goods = new EsGoods();
        goodsRepository.save(goods);
        return new ResultObject();
    }

    @PutMapping("/createGoodsIndex")
    public ResultObject createGoodsIndex(){

        boolean success = elasticsearchTemplate.createIndex(EsGoods.class);
        System.out.println("createGoodsIndex:"+success);
        return  new ResultObject<>(success);
    }

    @GetMapping("/indexGoodsData")
    public ResultObject indexGoodsData(){

        PageCondition<Goods> pageCondition = new PageCondition<>();
        pageCondition.setPageNum(1);
        pageCondition.setPageSize(50);
        pageCondition.setCondition(new Goods());
        ResultObject resultObject =  feignProductService.findGoodsPageList(pageCondition);
        if(resultObject.getResult()==0){
            return resultObject;
        }
        PageInfo<Goods> goodsPageInfo = (PageInfo<Goods>) resultObject.getData();
        int pageCount = goodsPageInfo.getPages();
        for (int i = 0; i < pageCount; i++) {
            if(i>0){
                pageCondition.setPageNum(i+1);
                ResultObject resultObjectPage  = feignProductService.findGoodsPageList(pageCondition);
                if(resultObject.getResult()==0){
                    return resultObject;
                }
                goodsPageInfo = (PageInfo<Goods>) resultObjectPage.getData();
            }
            List<Goods> goodsList = goodsPageInfo.getList();
            for (int j = 0; j < goodsList.size(); j++) {
                Goods goods = goodsList.get(j);
                goodsSearchService.updateGoodsIndex(goods.getGoodsId());
            }
        }
        return new ResultObject();
    }



}
