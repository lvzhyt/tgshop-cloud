package com.tg.shop.product.controller;

import com.opec.shop.core.entity.base.Goods;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class ProductController {

    @RequestMapping("/")
    public String home(){
        return "Welcome Product";
    }

    @GetMapping("/goods/{goodsId}")
    public Goods getGoods(@PathVariable String goodsId){
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setGoodsName("goodsName");
        goods.setGoodsSerial(goodsId);
        return goods;
    }
}
