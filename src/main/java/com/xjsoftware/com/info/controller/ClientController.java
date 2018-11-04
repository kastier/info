package com.xjsoftware.com.info.controller;

import com.xjsoftware.com.info.client.ClientInfo;
import com.xjsoftware.com.info.client.enums.StatusEnum;
import com.xjsoftware.com.info.manager.CookieHelper;
import com.xjsoftware.com.info.manager.CookieSet;
import com.xjsoftware.com.info.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
	
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	IClientService iClientService;

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String Index(Model model, HttpServletRequest request, @RequestParam (required = false) Integer id, HttpServletResponse res)
	{
		if(id==null)
		{
			String idInCookie=CookieHelper.getCookie(request,CookieSet.getClientCookieName());
			logger.info("id is null than from cookie:"+idInCookie);
			if(idInCookie==null)
			{
				return "login";
			}
			id=Integer.valueOf(idInCookie);
		}
		logger.info("curent id: "+String.valueOf(id));

		CookieHelper.writeCookie (res, CookieSet.getClientCookieName(),id.toString ());

		ClientInfo clientInfo=iClientService.getClientInfoById(id);
		if(clientInfo.getStatus()== StatusEnum.NEW)
		{
			model.addAttribute("phoneNumber",clientInfo.getPhoneNumber());
			return "addclient";
		}
		else
		{
			return "clientmain";
		}
	}
	
	@RequestMapping(value = "/login")
	public String Login(HttpServletRequest request)
	{
		//String result=CookieHelper.getCookie(request, CookieSet.getClientCookieName());

		return "login";
	}

	@RequestMapping(value = "clientperson")
	public String getPersonal(Model model,HttpServletRequest request){
		String idInCookie=CookieHelper.getCookie(request,CookieSet.getClientCookieName());
		logger.info("id is null than from cookie:"+idInCookie);
		if(idInCookie==null)
		{
			return "login";
		}
		Integer id=Integer.valueOf(idInCookie);
		ClientInfo clientInfo=iClientService.getClientInfoById(id);

		model.addAttribute("phoneNumber",clientInfo.getPhoneNumber());
		return "clientperson";
	}

	@RequestMapping(value = "clientmain")
	public String getMain(){
		return "clientmain";
	}
}
