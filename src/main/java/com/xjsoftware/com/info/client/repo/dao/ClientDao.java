package com.xjsoftware.com.info.client.repo.dao;

import com.xjsoftware.com.info.client.ClientInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClientDao {
    @Insert ({"INSERT INTO `infosys`.`clientInfo` (`phoneNumber`,`status`) VALUES (#{clientInfo.phoneNumber},#{clientInfo.status.value});"})
    @Options(useGeneratedKeys = true, keyProperty = "clientInfo.id")
    Integer addClientPhoneNumber(@Param ("clientInfo") ClientInfo clientInfo);


    @Update({""})
    ClientInfo updateClientByPhoneNumber(ClientInfo clientInfo);

    @Select ({"SELECT" ,
                            "clientInfo.id," ,
                            "clientInfo.phoneNumber," ,
                            "clientInfo.`name`," ,
                            "clientInfo.age," ,
                            "clientInfo.idCode," ,
                            "clientInfo.creaditScore," ,
                            "clientInfo.job," ,
                            "clientInfo.relativeName," ,
                            "clientInfo.relativePhone," ,
                            "clientInfo.relativeType," ,
                            "clientInfo.time," ,
                            "clientInfo.`status`" ,
                            "FROM" ,
                            "clientInfo by phoneNumber where phoneNumber =#{phoneNumber}"})
    ClientInfo  getClientByPhoneNumber(@Param (value = "phoneNumber") String phoneNumber);

    @Select ({"SELECT" ,
            "clientInfo.id," ,
            "clientInfo.phoneNumber," ,
            "clientInfo.`name`," ,
            "clientInfo.age," ,
            "clientInfo.idCode," ,
            "clientInfo.creaditScore," ,
            "clientInfo.job," ,
            "clientInfo.relativeName," ,
            "clientInfo.relativePhone," ,
            "clientInfo.relativeType," ,
            "clientInfo.time," ,
            "clientInfo.`status`" ,
            "FROM" ,
            "clientInfo where id =#{id}"})
    ClientInfo  getClientById(@Param ("id") Integer id);

    List<ClientInfo > getClientInfosByPage(Integer pageSize, Integer pageIndex);
}
