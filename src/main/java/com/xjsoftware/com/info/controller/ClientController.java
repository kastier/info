package com.xjsoftware.com.info.controller;

import com.xjsoftware.com.info.manager.CookieHelper;
import com.xjsoftware.com.info.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
	
	@RequestMapping(value = "/index")
	public String Index(HttpServletRequest request)
	{
		String clientId=CookieHelper.getCookie (request,"csess");
		
		Integer status=0;
		if(status==0)
		{
			return "client_info.html";
		}
		if(status==1)
		{
			return "client_info_add";
		}
		return "index";
	}
	
	@RequestMapping(value = "/login")
	public String Login(HttpServletRequest request)
	{
		return "login";
	}
}
