package com.xjsoftware.com.info.service.impl;

import com.alibaba.fastjson.JSON;
import com.xjsoftware.com.info.facede.sms.request.SmsSendRequest;
import com.xjsoftware.com.info.facede.sms.response.SmsSendResponse;
import com.xjsoftware.com.info.facede.sms.util.ChuangLanSmsUtil;
import com.xjsoftware.com.info.service.ISMSService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class SMSService implements ISMSService {

    private static final String charset = "utf-8";
    // 请登录zz.253.com 获取创蓝API账号(非登录账号,示例:N1234567)
    private static String account = "N4454036";
    // 请登录zz.253.com 获取创蓝API密码(非登录密码)
    private static String pswd = "1231232";

    private static String companyName="测试系统";

    private String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
    @Override
    public String sendSMS(String phoneNumber,String validCode)
    {
        String msg = String.format("【%s】你好,你的验证码是%s",companyName,validCode);
        //状态报告
        String report= "true";
        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phoneNumber,report);
        String requestJson = JSON.toJSONString(smsSingleRequest);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

        return smsSingleResponse.getCode();
    }
}
