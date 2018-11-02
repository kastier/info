package com.xjsoftware.com.info.controller;


import com.xjsoftware.com.info.client.ClientInfo;
import com.xjsoftware.com.info.volobj.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "api/client")
@RestController
public class ClientApi {

    @RequestMapping(value ="/update")
    public ApiResult addClientInfo(ClientInfo clientInfo)
    {
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
