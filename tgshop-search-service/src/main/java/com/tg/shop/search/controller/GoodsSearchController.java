package com.tg.shop.search.controller;

import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.entity.EsGoods;
import com.tg.shop.search.service.GoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/es")
public class GoodsSearchController {


    @Autowired
    private GoodsSearchService goodsSearchService;




    @GetMapping("/searchGoods")
    public ResultObject<Page<EsGoods>> searchGoods(@RequestParam("search") String search,
                                  @RequestParam(required = false,defaultValue = "1") int pageNum,
                                  @RequestParam(required = false,defaultValue = "10")int pageSize){
        Page<EsGoods> page = goodsSearchService.searchGoods(search,pageNum,pageSize);
        return new ResultObject<>(page);
    }

}
