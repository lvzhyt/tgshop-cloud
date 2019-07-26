package com.tg.shop.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.auth.feign.service.FeignMessageQueueService;
import com.tg.shop.auth.request.LoginParam;
import com.tg.shop.auth.request.RegisterParam;
import com.tg.shop.auth.service.MemberService;
import com.tg.shop.auth.service.SellerService;
import com.tg.shop.core.annotation.AnonymousAccess;
import com.tg.shop.core.domain.sms.SmsInfo;
import com.tg.shop.core.domain.sms.SmsMessage;
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
    @Resource
    private FeignMessageQueueService feignMessageQueueService;

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
    public ResultObject register(@RequestBody @Valid RegisterParam registerParam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResultObject(ErrorCode.REQUEST_ERROR,bindingResult.getFieldErrors());
        }
        String code = registerParam.getCode();
        String key = "sms-"+registerParam.getMobile();
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        if(!code.equals(redisCode)){
            return new ResultObject(ErrorCode.REGISTER_CODE_ERROR);
        }
        LoginParam loginParam = new LoginParam();
        loginParam.setUserName(registerParam.getMobile());
        loginParam.setPassword(registerParam.getPassword());
        ResultObject result = memberService.registerByPhone(loginParam);
        stringRedisTemplate.delete(key);

        return result;
    }

    @ApiOperation("获取注册短信验证码")
    @AnonymousAccess
    @GetMapping("/getRegisterSmsCode")
    public ResultObject getRegisterSmsCode(@RequestParam String phone){
        String code = String.valueOf(RandomUtils.nextInt(1000,9999));
        // 发送MQ消息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        String param = jsonObject.toJSONString();
        SmsMessage smsMessage = new SmsMessage(SmsInfo.TEMPLATE_REGISTER_CODE,phone,SmsInfo.SIGN_NAME,param,null);
        ResultObject resultObject = feignMessageQueueService.sendSmsMessage(smsMessage);
        log.info("获取注册短信验证码: phone: "+phone+" code: "+code);
        return resultObject;
    }
}
