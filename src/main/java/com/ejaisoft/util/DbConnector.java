package com.ejaisoft.util;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnector {
    public boolean dbConnection(String url , String userName,String password){

        try{

            //register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,userName,password);

            Statement statement   = connection.createStatement();

            return true;


        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    static void main() {
        DbConnector conn = new DbConnector();

        boolean hasConnected = conn.dbConnection("jdbc:mysql://localhost:3306/hostel_system","root","A#1rnold233");

        if(hasConnected) {
            System.out.println("db connected successfully");
        }else{
            System.out.println("COnnection failed");
        }
    }

}
