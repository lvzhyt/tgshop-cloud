package com.tg.shop.auth.controller;

import com.tg.shop.auth.request.LoginParam;
import com.tg.shop.auth.service.SellerService;
import com.tg.shop.core.domain.ResultState;
import com.tg.shop.core.domain.auth.cache.SellerUser;
import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.domain.auth.entity.User;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/seller")
public class SellerAuthController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private SellerService sellerService;

    @PostMapping("/login")
    public ResultObject login(@RequestBody @Valid LoginParam loginParam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultObject.getInstance(ErrorCode.REQUEST_ERROR,bindingResult.getFieldErrors());
        }
        ResultObject resultState = sellerService.login(loginParam);
        return resultState;
    }


    @GetMapping("/userInfo")
    public ResultObject getInfo(@RequestParam String token){
        if(!redisTemplate.hasKey(token)){
            return ResultObject.getInstance(ErrorCode.TOKEN_LOSE_EFFICACY);
        }
        SellerUser sellerUser = (SellerUser) redisTemplate.opsForValue().get(token);
        Seller seller = sellerUser.getSeller();
        seller.setSellerId("001");
        seller.setSellerName("Seller");
        User user = new User();
        user.setUserId(seller.getSellerId());
        user.setUserName(seller.getSellerName());
        user.setRoles(new String[]{"super_admin", "admin"});
        user.setAvator("https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png");
        return ResultObject.getInstance(user);
    }

}
