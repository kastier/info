package com.xjsoftware.com.info.client.repo.dao;

import com.xjsoftware.com.info.client.ClientInfo;

import java.util.List;

public interface ClientDao {
    Integer addClient(ClientInfo clientInfo);

    Integer updateClient(ClientInfo clientInfo);

    ClientInfo  getClientByPhoneNumber(ClientInfo clientInfo);

    List<ClientInfo > getClientInfosByPage(Integer pageSize, Integer pageIndex);
}
