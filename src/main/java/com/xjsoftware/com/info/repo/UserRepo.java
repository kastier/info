package com.xjsoftware.com.info.repo;

import  java.sql.*;
import  java.io.*;

import java.sql.Connection;
import java.util.Properties;

public class UserRepo {
    public  int getStatus(int id)
    {
        java.sql.Statement statement;
        java.sql.Connection conn=getConnection();
        return 0;
    }
    public static Connection getConnection()
    {
        Properties properties=new Properties();

    }
}
