package com.xjsoftware.com.info.client.repo;

import com.xjsoftware.com.info.client.ClientInfo;
import com.xjsoftware.com.info.client.repo.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientRepository {

    @Autowired
    ClientDao clientDao;
    public Integer addClient(ClientInfo clientInfo)
    {
        return 0;
        
    }
    public Integer addClientPhoneNumber (String phoneNumber)
    {
        ClientInfo clientInfo=new ClientInfo ();
        clientInfo.setPhoneNumber (phoneNumber);
         clientDao.addClientPhoneNumber (clientInfo);
         return clientInfo.getId ();
    }
    
    
}
