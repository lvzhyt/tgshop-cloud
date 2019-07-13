package com.tg.shop.search.controller;

import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.entity.EsGoods;
import com.tg.shop.search.service.GoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@RestController
public class GoodsSearchController {


    @Autowired
    private GoodsSearchService goodsSearchService;




    @GetMapping("/es/searchGoods")
    public ResultObject<Page<EsGoods>> searchGoods(@RequestParam("search") String search,
                                  @RequestParam(required = false,defaultValue = "1") int pageNum,
                                  @RequestParam(required = false,defaultValue = "10")int pageSize){
        Page<EsGoods> page = goodsSearchService.searchGoods(search,pageNum,pageSize);
        return new ResultObject<>(page);
    }


    /**
     * 更新商品索引
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/es/updateGoodsSearchIndex",method = RequestMethod.GET)
    public ResultObject updateGoodsSearchIndex(@RequestParam(value = "goodsId") String goodsId){
        ResultObject resultObject = goodsSearchService.updateGoodsIndex(goodsId);
        return resultObject;
    }

}
