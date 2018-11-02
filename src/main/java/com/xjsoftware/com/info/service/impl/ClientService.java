package com.xjsoftware.com.info.service.impl;

import com.xjsoftware.com.info.client.ClientInfo;
import com.xjsoftware.com.info.client.repo.ClientRepository;
import com.xjsoftware.com.info.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class ClientService  implements IClientService
{
	@Autowired
	ClientRepository clientRepository;
	
	public Integer addClientPhoneNumber (String phoneNumber)
	{
		clientRepository.addClientPhoneNumber (phoneNumber);
		return 0;
	}
	public Integer setClientInfo(ClientInfo clientInfo)
	{
		return 0;
	}
}
