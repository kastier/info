package com.xjsoftware.com.info.controller;


import com.xjsoftware.com.info.client.ClientInfo;
import com.xjsoftware.com.info.volobj.ApiResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/client/api")
@RestController
public class ClientApi {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value ="/update")
    public ApiResult addClientInfo(@RequestBody ClientInfo clientInfo)
    {
        logger.info(clientInfo.getJob());
        ApiResult apiResult=new ApiResult();

        return new ApiResult();
    }

    @RequestMapping(value ="/reg")
    public ApiResult regClientInfo(ClientInfo clientInfo)
    {
        if(clientInfo!=null&&clientInfo.getPhoneNumber()!=null&&clientInfo.getPhoneNumber().length()>0)
        {

        }
        ApiResult apiResult=new ApiResult();

        return new ApiResult();
    }
}
