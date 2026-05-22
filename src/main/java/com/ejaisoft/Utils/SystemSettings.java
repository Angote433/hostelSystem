package com.ejaisoft.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SystemSettings {
    public Connection dbConnection(String url , String userName,String password){
        Connection conn;

        try{

            //register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url,userName,password);
            return conn;


        }catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }




}
