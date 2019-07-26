package com.tg.shop.mq.sms.aliyun.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tg.shop.core.domain.sms.SmsMessage;
import com.tg.shop.core.domain.sms.SmsResponse;
import com.tg.shop.mq.config.DysmsProperties;
import com.tg.shop.mq.sms.aliyun.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 大于短信
 * 启动 打开@Service
 */
@Slf4j
//@Service
public class DysmsServiceImpl implements SmsService {

//    @Value("aliyun.dysms.accessKeyId")
//    String accessKeyId;

    @Autowired
    private DysmsProperties dysmsProperties;

    @Override
    public SmsResponse sendMessage(SmsMessage smsMessage) {
        /**
         * // demo
         * {
         *   "outId": "",
         *   "phoneNumbers": "18610239332",
         *   "signName": "糖果贝贝",
         *   "templateCode": "SMS_151905513",
         *   "templateParam": "{\"code\":\"123456\"}"
         * }
         */
        log.info("阿里云 发送短消息.");
        SmsResponse response = new SmsResponse();
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", dysmsProperties.getAccessKeyId(), dysmsProperties.getAccessKeySecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(smsMessage.getPhoneNumbers());
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(smsMessage.getSignName());
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(smsMessage.getTemplateCode());
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(smsMessage.getTemplateParam());

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId(smsMessage.getOutId());

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            int result = 0;
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                result = 1;
            }
            response = new SmsResponse(result, sendSmsResponse.getRequestId(), sendSmsResponse.getBizId(), sendSmsResponse.getCode(), sendSmsResponse.getCode());

        } catch (ClientException e) {
            response.setResult(0);
            response.setCode(e.getErrCode());
            response.setMessage("阿里云发送短信异常." + e.getErrCode());
            log.error("阿里云发送短信异常." + e.getErrCode() + e.getMessage(), e);
        }

        return response;
    }


}
