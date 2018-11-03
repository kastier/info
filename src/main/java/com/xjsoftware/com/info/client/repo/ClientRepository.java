package com.xjsoftware.com.info.client.repo;

import com.xjsoftware.com.info.client.ClientInfo;
import com.xjsoftware.com.info.client.enums.StatusEnum;
import com.xjsoftware.com.info.client.repo.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientRepository {

    @Autowired
    ClientDao clientDao;

    public Integer setClient(ClientInfo clientInfo)
    {

        ClientInfo _info=new ClientInfo();
        _info=clientDao.updateClientByPhoneNumber(clientInfo);
        return _info.getId();
        
    }

    public Integer addClientPhoneNumber (ClientInfo clientInfo)
    {
      clientDao.addClientPhoneNumber (clientInfo);
         return clientInfo.getId ();
    }

    public ClientInfo  getClientById(Integer id)
    {
        return clientDao.getClientById(id);
    }
}
