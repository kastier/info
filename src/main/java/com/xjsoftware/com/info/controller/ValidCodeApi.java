package com.xjsoftware.com.info.controller;


import com.xjsoftware.com.info.client.ClientInfo;
import com.xjsoftware.com.info.client.enums.StatusEnum;
import com.xjsoftware.com.info.manager.CookieHelper;
import com.xjsoftware.com.info.manager.CookieSet;
import com.xjsoftware.com.info.service.IClientService;
import com.xjsoftware.com.info.service.IValidCodeService;
import com.xjsoftware.com.info.volobj.ApiResult;
import com.xjsoftware.com.info.volobj.ValidCodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/validcode/api")
public class ValidCodeApi {
	
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IValidCodeService validCodeService;
	
	@RequestMapping(value = "/send")
	public ApiResult sendValidCode(@RequestParam String phoneNumber)
	{
	
	ApiResult apiResult=new ApiResult ();
	try
	{
		ValidCodeInfo result=validCodeService.sendValidCode (phoneNumber);
		apiResult.setCode (result.getStatus().getValue());
		apiResult.setData(result.getStatus().getDesc());
		return apiResult;
	}
	catch (Exception exe)
	{
	exe.printStackTrace ();
	}
	apiResult.setCode (-1);
		apiResult.setMsg ("fail");
		return apiResult;
	}
	
	@Autowired
	IClientService iClientService;
	
	@RequestMapping(value = "/check",method = RequestMethod.GET)
	public ApiResult checkValidCode(@RequestParam String phoneNumber, @RequestParam String validCode)
	{
		ApiResult apiResult=new ApiResult ();
		Boolean result=validCodeService.isValidCodeCorrect (phoneNumber,validCode);
		if(result)
		{
			ClientInfo clientInfo=iClientService.getClientInfoByPhone(phoneNumber);

			Integer id=0;
			if(clientInfo==null||clientInfo.getId()<=0)
			{
				logger.info("new user");
				id=iClientService.addClientPhoneNumber (phoneNumber);
			}
			else {
				id=clientInfo.getId();
			}
			apiResult.setData (id);
			apiResult.setCode (1);
			return apiResult;
		}
		else {
			apiResult.setCode(-1);
			apiResult.setMsg("请输入正确的验证码");
			return apiResult;
		}
	}
}
