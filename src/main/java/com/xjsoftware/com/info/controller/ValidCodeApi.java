package com.xjsoftware.com.info.controller;


import com.xjsoftware.com.info.manager.CookieHelper;
import com.xjsoftware.com.info.service.IClientService;
import com.xjsoftware.com.info.service.IValidCodeService;
import com.xjsoftware.com.info.volobj.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		Integer result=validCodeService.sendValidCode (phoneNumber).getStatus ().getValue ();
		apiResult.setCode (result);
		apiResult.setMsg ("success");
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
	
	@RequestMapping(value = "check",method = RequestMethod.GET)
	public ApiResult checkValidCode(@RequestParam String phoneNumber,@RequestParam String validCode,HttpServletResponse res)
	{
		Boolean result=validCodeService.isValidCodeCorrect (phoneNumber,validCode);
		if(result)
		{
			Integer id=iClientService.addClientPhoneNumber (phoneNumber);
			CookieHelper.writeCookie (res,"csec",id.toString ());
			ApiResult apiResult=new ApiResult ();
			apiResult.setData ("success");
			apiResult.setCode (1);
			return apiResult;
		}
		ApiResult apiResult=new ApiResult ();
		apiResult.setCode (0);
		apiResult.setData ("faile");
		return apiResult;
	}
}
