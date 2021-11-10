package com.example.staff;

import java.sql.DriverManager;
import java.sql.*;


public class DatabaseConnection {
    public Connection databaseLink;

    public  Connection getConnection(){
        String databaseName = "demo_db";
        String databaseUser = "root";
        String databasePassword = "48BarnHall23$";
        String url = "jdbc:mysql://localhost/" + databaseName ;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }

}
