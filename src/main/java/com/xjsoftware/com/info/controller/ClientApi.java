package com.xjsoftware.com.info.controller;


import com.xjsoftware.com.info.client.ClientInfo;
import com.xjsoftware.com.info.client.enums.StatusEnum;
import com.xjsoftware.com.info.service.IClientService;
import com.xjsoftware.com.info.volobj.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/client/api")
@RestController
public class ClientApi {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    IClientService iClientService;
    @RequestMapping(value ="/update")
    public ApiResult updateClientInfo(@RequestBody ClientInfo clientInfo)
    {
        ApiResult apiResult=new ApiResult();
        clientInfo.setStatus(StatusEnum.VALID);
        Integer result=0;

        try
        {
           result= iClientService.updateClientInfoByPhoneNumber(clientInfo);
        }
        catch (Exception exe)
        {

            exe.printStackTrace();
            apiResult.setCode(-1);
            apiResult.setMsg("信息异常，请联系管理员");
            return apiResult;
        }

        logger.info(clientInfo.getJob());
        if(result>0)
        {
            apiResult.setCode(1);
            apiResult.setMsg("添加成功");
        }
        else {

            apiResult.setCode(0);
            apiResult.setMsg("添加失败");
        }
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
