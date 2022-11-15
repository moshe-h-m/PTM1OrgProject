package com.example.ptm1orgproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class dbConnection {

    Connection con;
    Statement statement;
//DESKTOP-H7TN2KA\SQLSERVER:1434
    public void dbConnect() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String localhost="DESKTOP-H7TN2KA";

        String OurOrg1="OurOrg";
        String connectionUrl = "jdbc:sqlserver://"+localhost+":1433;database="+OurOrg1+";integratedSecurity=true;encrypt=false;trustServerCertificate=true;";
        con = DriverManager.getConnection(connectionUrl);
        System.out.println("connected");
        statement = con.createStatement();
    }
//    public void runQueryUpdate(String query) throws SQLException {
//        String queryString = query;
//        int updatestat = statement.executeUpdate(queryString);
//        System.out.println(updatestat);
//    }

    public Connection getCon() {
        return con;
    }
}
