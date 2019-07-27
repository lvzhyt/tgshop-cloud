package com.tg.shop.trade.controller;

import com.tg.shop.core.annotation.UserToken;
import com.tg.shop.core.domain.auth.entity.Member;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.trade.entity.Cart;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.CacheMemberHolderLocal;
import com.tg.shop.trade.feign.service.FeignProductService;
import com.tg.shop.trade.request.param.OrderParam;
import com.tg.shop.trade.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@UserToken
@Api("订单交易")
@RestController
@RequestMapping("/trade")
public class TradeCartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private IdGenerator idGenerator;
    @Resource
    private FeignProductService feignProductService;


    @ApiOperation("添加购物车")
    @PostMapping("/addCart")
    public ResultObject addCart(@RequestParam String skuId){
        Member member = CacheMemberHolderLocal.getMember();


        ResultObject<GoodsSkuDetailResultVo>  skuDetailResultVoResultObject = feignProductService.getSkuDetailBySkuId(skuId);
        if(skuDetailResultVoResultObject.getResult()==0){
            return skuDetailResultVoResultObject;
        }
        GoodsSkuDetailResultVo vo = skuDetailResultVoResultObject.getData();
        if(vo==null){
            return new ResultObject(ErrorCode.EMPTY_DATA_ERROR);
        }
        Cart record = cartService.findCartByMemberSkuId(member.getMemberId(),skuId);
        if(record!=null){
            if(record.getGoodsNum()+1>vo.getInventoryNum()){
                return new ResultObject(ErrorCode.TRADE_NOT_ENOUGH_INVENTORY);
            }
            record.setGoodsNum(record.getGoodsNum()+1);
            record.setModifyTime(new Date());
            record.setModifier(member.getMemberId());
            int count = cartService.updateCart(record);
            return new ResultObject(count);
        }

        Cart cart = new Cart();
        cart.setCartId(idGenerator.nextStringId());
        cart.setMemberId(member.getMemberId());
        cart.setSkuId(skuId);
        cart.setGoodsId(vo.getGoodsId());
        cart.setGoodsName(vo.getSkuGoodsName());
        cart.setStoreId(vo.getStoreId());
        cart.setStoreName(vo.getStoreName());
        cart.setSpecInfo(vo.getSpecInfo());
        cart.setGoodsNum(1);
        cart.setGoodsPrice(vo.getSalePrice());
        cart.setCreator(member.getMemberId());
        cart.setCreateTime(new Date());

        int count = cartService.saveCart(cart);

        return new ResultObject(count);
    }
}

