package com.tg.shop.manager.seller.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.manager.seller.request.ErrorInfoParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class SellerErrorLogController {

    @PostMapping("/errorLog")
    public ResultObject saveErrorLogger(@RequestBody ErrorInfoParam errorInfo){
        log.error(JSONObject.toJSONString(errorInfo));
        return ResultObject.getInstance();
    }
}
