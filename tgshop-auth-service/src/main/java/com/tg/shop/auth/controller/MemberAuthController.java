package com.tg.shop.auth.controller;

import com.tg.shop.auth.request.LoginParam;
import com.tg.shop.auth.service.MemberService;
import com.tg.shop.auth.service.SellerService;
import com.tg.shop.core.annotation.AnonymousAccess;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * 用户
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberAuthController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MemberService memberService;

    @ApiOperation("密码登录")
    @AnonymousAccess
    @PostMapping("/login")
    public ResultObject login(@RequestBody @Valid LoginParam loginParam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResultObject(ErrorCode.REQUEST_ERROR,bindingResult.getFieldErrors());
        }
        String token = memberService.login(loginParam);
        return new ResultObject(token);
    }


    @ApiOperation("注册")
    @AnonymousAccess
    @PostMapping("/register")
    public ResultObject register(@RequestBody @Valid LoginParam loginParam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResultObject(ErrorCode.REQUEST_ERROR,bindingResult.getFieldErrors());
        }
        ResultObject result = memberService.registerByPhone(loginParam);
        return result;
    }

    @ApiOperation("获取注册短信验证码")
    @AnonymousAccess
    @GetMapping("/getRegisterSmsCode")
    public ResultObject getRegisterSmsCode(@RequestParam String phone){
        String code = String.valueOf(RandomUtils.nextInt(1000,9999));
        String key = "smsCode-"+phone;
        stringRedisTemplate.opsForValue().set(key,code,90, TimeUnit.SECONDS);
        // 发送MQ消息
        log.info("获取注册短信验证码: phone: "+phone+" code: "+code);
        return new ResultObject();
    }
}
