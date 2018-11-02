package com.xjsoftware.com.info.client.repo.dao;

import com.xjsoftware.com.info.client.ClientInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClientDao {
    @Insert ({"INSERT INTO `infosys`.`client_info`(`phoneNumber`) VALUES (#{phoneNumber});"})
    Integer addClientPhoneNumber(@Param (value = "phoneNumber") String phoneNumber);

    Integer updateClient(ClientInfo clientInfo);

    @Select ({
                    "SELECT" ,
                            "client_info.id," ,
                            "client_info.phoneNumber," ,
                            "client_info.`name`," ,
                            "client_info.age," ,
                            "client_info.idCode," ,
                            "client_info.creaditScore," ,
                            "client_info.job," ,
                            "client_info.relativeName," ,
                            "client_info.relativePhone," ,
                            "client_info.relativeType," ,
                            "client_info.time," ,
                            "client_info.`status`" ,
                            "FROM" ,
                            "client_info by phoneNumber where phoneNumber =#{phoneNumber}"
            })
    ClientInfo  getClientByPhoneNumber(@Param (value = "phoneNumber") String phoneNumber);

    List<ClientInfo > getClientInfosByPage(Integer pageSize, Integer pageIndex);
}
