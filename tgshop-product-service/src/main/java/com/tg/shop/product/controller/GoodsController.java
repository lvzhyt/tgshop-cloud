package com.tg.shop.product.controller;

import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.product.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods/getGoodsById")
    public ResultObject<Goods> getGoodsById(String goodsId){
        Goods goods =  goodsService.getGoodsById(goodsId);
        return new ResultObject<>(goods);
    }


    @PostMapping("/goods/findGoodsPageList")
     ResultObject<PageInfo<Goods>> findGoodsPageList(PageCondition<Goods> pageCondition){
        PageInfo<Goods> page =  goodsService.findGoodsPageList(pageCondition);
        return new ResultObject<>(page);
    }
}
