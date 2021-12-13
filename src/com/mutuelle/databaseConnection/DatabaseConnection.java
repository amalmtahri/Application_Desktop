package com.mutuelle.databaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection(){
    	  String databaseName = "mutuelle_centralisee";
    	  
          String databaseUser = "root";
          String databasePassword = "amal";
          String url = "jdbc:mysql://localhost/" + databaseName;
          try{
              Class.forName("com.mysql.cj.jdbc.Driver");
              databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
          }catch (Exception e){
              e.getStackTrace();
              e.getCause();
          }
          return databaseLink;
    }
}

