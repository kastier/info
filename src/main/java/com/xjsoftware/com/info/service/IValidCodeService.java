package com.xjsoftware.com.info.service;

import com.xjsoftware.com.info.volobj.ValidCodeInfo;

public interface IValidCodeService
{
	ValidCodeInfo sendValidCode(String phoneNumber) throws Exception;
	Boolean isValidCodeCorrect(String phneNumber,String validCode);
}