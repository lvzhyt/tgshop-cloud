package com.tg.shop.trade.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.annotation.UserToken;
import com.tg.shop.core.domain.auth.entity.Member;
import com.tg.shop.core.domain.auth.entity.Store;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.trade.entity.*;
import com.tg.shop.core.domain.trade.vo.CartDetailVo;
import com.tg.shop.core.domain.trade.vo.StoreSimpleVo;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.CacheMemberHolderLocal;
import com.tg.shop.trade.exception.TradeException;
import com.tg.shop.trade.feign.service.FeignProductService;
import com.tg.shop.trade.request.param.AddressParam;
import com.tg.shop.trade.request.param.ConfirmOrderParam;
import com.tg.shop.trade.request.param.OrderParam;
import com.tg.shop.trade.service.CartService;
import com.tg.shop.trade.service.FreightChargeTempTradeService;
import com.tg.shop.trade.service.UserReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Administrator
 */
@UserToken
@Api("订单交易")
@RestController
@RequestMapping("/trade")
public class TradeOrderController {


    @Autowired
    private IdGenerator idGenerator;
    @Resource
    private FeignProductService feignProductService;
    @Autowired
    private UserReceiveAddressService userReceiveAddressService;
    @Autowired
    private FreightChargeTempTradeService freightChargeTempTradeService;
    @Autowired
    private CartService cartService;

    /**
     * 订单确认页面
     * 购物车结算、商品页面直接购买结算--》订单确认页面
     * 填写订单信息：地址、支付方式、发票、卡券、积分、选择优惠券、计算运费
     * @return
     */
    @ApiOperation("确认订单")
    @PostMapping("/confirmOrder")
    public ResultObject confirmOrder(@Valid @RequestBody ConfirmOrderParam confirmOrderParam) throws TradeException {
        System.out.println(confirmOrderParam);

        List<Cart> cartList = cartService.findCartByIds(confirmOrderParam.getCartIds());
        JSONObject data = new JSONObject();

        // 收货地址
        Member member = CacheMemberHolderLocal.getMember();
        UserReceiveAddress condition = new UserReceiveAddress();
        condition.setMemberId(member.getMemberId());
        UserReceiveAddress defaultAddress = userReceiveAddressService.getMemberDefaultAddress(member.getMemberId());

        data.put("address",defaultAddress);
        List<CartDetailVo> cartDetailVoList = getSkuDetailList(cartList);
        Map<String,List<CartDetailVo>> storeCartMap = new HashMap<>(16);
        List<StoreSimpleVo> storeSimpleVoList = new ArrayList<>();
        for (CartDetailVo vo :
                cartDetailVoList) {
            String key = vo.getStoreId();
            if (storeCartMap.containsKey(key)) {
                storeCartMap.get(key).add(vo);
            } else {
                List<CartDetailVo> list = new ArrayList<>();
                list.add(vo);
                storeCartMap.put(key, list);
                StoreSimpleVo storeSimpleVo = new StoreSimpleVo(key,vo.getStoreName());
                storeSimpleVoList.add(storeSimpleVo);
            }
        }
        data.put("storeSimpleVoList",storeSimpleVoList);
        data.put("storeCartMap",storeCartMap);

        // 可用优惠券
        JSONObject coupon = new JSONObject();
        List couponList = new ArrayList();
        data.put("usedCoupon",coupon);
        data.put("couponList",couponList);

        // 计算运费
        int shipFee = this.calculateShipFee(storeCartMap,0,member);
        data.put("shipFee",shipFee);
        // 防止重复提交
        data.put("orderId",idGenerator.nextStringId());


        return new ResultObject<>(data);
    }

    /**
     * 提交订单
     *  地址、 商品 skuIds  goodsNum、支付方式、优惠券、积分、卡券
     *  ==>> 计算得出总金额
     * @param orderParam
     * @param bindingResult
     * @return
     */
    @ApiOperation("提交订单")
    @PostMapping("/saveOrder")
    public ResultObject saveOrder(@Valid @RequestBody OrderParam orderParam, BindingResult bindingResult) throws TradeException {
        if(bindingResult.hasErrors()){
            return new ResultObject<>(ErrorCode.REQUEST_PARAM_ERROR,bindingResult.getFieldErrors());
        }
        List<Cart> cartList = cartService.findCartByIds(orderParam.getCartIds());
        List<CartDetailVo> skuList = getSkuDetailList(cartList);
        if(skuList==null|| skuList.isEmpty()){
            return new ResultObject(ErrorCode.EMPTY_DATA_ERROR);
        }
        //
        Order order = new Order();
        List<OrderItems> orderItemsList = new ArrayList<>();



        return new ResultObject();
    }

    @ApiOperation("获取收货地址")
    @GetMapping("/getReceiveAddressList")
    public ResultObject<List<UserReceiveAddress>> getReceiveAddressList(){
        Member member = CacheMemberHolderLocal.getMember();
        UserReceiveAddress condition = new UserReceiveAddress();
        condition.setMemberId(member.getMemberId());
        List<UserReceiveAddress> list = userReceiveAddressService.findAddressByCondition(condition);
        return new ResultObject<>(list);
    }

    @ApiOperation("保存收货地址")
    @PostMapping("/saveReceiveAddress")
    public ResultObject saveReceiveAddress(@Valid @RequestBody AddressParam addressParam,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResultObject<>(ErrorCode.REQUEST_PARAM_ERROR,bindingResult.getFieldErrors());
        }
        Member member = CacheMemberHolderLocal.getMember();

        UserReceiveAddress userReceiveAddress = new UserReceiveAddress();
        BeanUtils.copyProperties(addressParam,userReceiveAddress);
        userReceiveAddress.setAddressId(idGenerator.nextStringId());
        userReceiveAddress.setMemberId(member.getMemberId());
        userReceiveAddress.setCreateTime(new Date());
        userReceiveAddress.setCreator(member.getMemberId());

        int result = userReceiveAddressService.saveAddress(userReceiveAddress);
        if(addressParam.getUseDefault()==1){
            // 取消其他默认地址 设置默认地址
            userReceiveAddressService.setDefaultReceiveAddress(userReceiveAddress.getAddressId());
        }
        return new ResultObject<>(userReceiveAddress);
    }

    @ApiOperation("设置默认收货地址")
    @PostMapping("/setDefaultReceiveAddress")
    public ResultObject setDefaultReceiveAddress(@RequestParam String addressId){
        // 取消其他默认地址 设置默认地址
        boolean success = userReceiveAddressService.setDefaultReceiveAddress(addressId);
        return new ResultObject<>(success);
    }




    private List<CartDetailVo> getSkuDetailList(List<Cart> cartList) throws TradeException {
        List<CartDetailVo> result = new ArrayList<>();
        for (Cart cart:cartList) {
            ResultObject<GoodsSkuDetailResultVo>  skuDetailResultVoResultObject = feignProductService.getSkuDetailBySkuId(cart.getSkuId());
            if(skuDetailResultVoResultObject.getResult()==0){
                throw new TradeException(skuDetailResultVoResultObject.getMessage());
            }
            CartDetailVo cartDetailVo = new CartDetailVo();
            BeanUtils.copyProperties(cart,cartDetailVo);
            GoodsSkuDetailResultVo skuDetail = skuDetailResultVoResultObject.getData();
            BeanUtils.copyProperties(skuDetail,cartDetailVo);
            result.add(cartDetailVo);
        }
        return  result;
    }

    /**
     * 计算运费
     * member 根据会员等级、plus 判断包邮
     * 同一店铺：计算最低邮费
     * 最终邮费：所有店铺邮费的和
     * 最低邮费：一个包邮即所有包邮；满额包邮：店铺最低满额值；支付免邮：订单支付方式；不包邮：最低邮费不累加
     * @return
     */
    private int calculateShipFee(Map<String,List<CartDetailVo>> storeCartMap,int payType, Member member){
        int shipFee = 0;
        Iterator<String> it = storeCartMap.keySet().iterator();
        while(it.hasNext()){
            List<CartDetailVo> list = storeCartMap.get(it.next());
            int storeShipFee = 0;
            BigDecimal goodsAmount = this.calculateGoodsAmount(list,member);
            for (CartDetailVo sku:list) {
                String templateId = sku.getTemplateId();
                FreightChargeTempTrade freightChargeTempTrade = freightChargeTempTradeService.getFreightChargeTemplateById(templateId);
                int type = freightChargeTempTrade.getTemplateType();
                if(type==1){
                    // 包邮返回运费0
                    storeShipFee = 0;
                    break;
                }else if(type==2){
                    // 满额包邮
                    if(goodsAmount.doubleValue()>freightChargeTempTrade.getFulfilPrice().doubleValue()){
                        // 店铺订单商品满足包邮，返回运费0
                        storeShipFee = 0;
                        break;
                    }else{
                        int fee = freightChargeTempTrade.getFreightPrice().intValue();
                        if(storeShipFee==0){
                            storeShipFee = fee;
                        }
                        // 取最小值
                        storeShipFee = storeShipFee<fee?storeShipFee:fee;
                    }
                }else if(type==3){
                    // 在线支付免邮
                    if(payType==0){
                        storeShipFee=0;
                        break;
                    }else{
                        storeShipFee = freightChargeTempTrade.getFreightPrice().intValue();
                    }
                }else if(type==4){
                    // 不包邮
                    int fee = freightChargeTempTrade.getFreightPrice().intValue();
                    if(storeShipFee==0){
                        storeShipFee = fee;
                    }
                    // 取最小值
                    storeShipFee = storeShipFee<fee?storeShipFee:fee;
                }

            }
            // 运费 = 店铺运费的和
            shipFee +=storeShipFee;
        }

        return shipFee;
    }

    private BigDecimal calculateGoodsAmount(List<CartDetailVo> skuList, Member member){
        // 未考虑促销活动
        BigDecimal goodsAmount = new BigDecimal("0");
        for (CartDetailVo sku:skuList) {
            BigDecimal price = sku.getSalePrice();
            if(sku.getPlusPriceOpen()==1 && member.getPlus()==1){
                price = sku.getPlusPrice();
            }
            // 单价*数量
            goodsAmount.add(price.multiply(new BigDecimal(sku.getGoodsNum())));
        }
        return goodsAmount;
    }



    /**
     * 计算优惠券
     */
    private Object caculateCoupon(List<CartDetailVo> skuList,Member member){
        return null;
    }

}

