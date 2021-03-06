package com.xjsoftware.com.info.service;

import com.xjsoftware.com.info.client.ClientInfo;

public interface IClientService {
	Integer addClientPhoneNumber (String phoneNumber);
	Integer setClientInfo(ClientInfo clientInfo);
	ClientInfo getClientInfoByPhone(String phoneNumber);
	Integer updateClientInfoByPhoneNumber(ClientInfo clientInfo);

	ClientInfo getClientInfoById(Integer id);
}
