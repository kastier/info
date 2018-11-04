package com.xjsoftware.com.info.service.impl;

import com.xjsoftware.com.info.client.ClientInfo;
import com.xjsoftware.com.info.client.enums.StatusEnum;
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

	@Override
	public Integer addClientPhoneNumber (String phoneNumber)
	{
		ClientInfo clientInfo=new ClientInfo();
		clientInfo.setPhoneNumber(phoneNumber);
		clientInfo.setStatus(StatusEnum.NEW);
		return clientRepository.addClientPhoneNumber (clientInfo);
	}
	public Integer setClientInfo(ClientInfo clientInfo)
	{
		return 0;
	}

	@Override
	public ClientInfo getClientInfoByPhone(String phoneNumber)
	{
		return null;
	}

	@Override
	public ClientInfo getClientInfoById(Integer id)
	{
		return clientRepository.getClientById(id);
	}

	public Integer updateClientInfoByPhoneNumber(ClientInfo clientInfo)
	{
		return clientRepository.updateClientInfoByPhoneNumber(clientInfo);
	}
}
