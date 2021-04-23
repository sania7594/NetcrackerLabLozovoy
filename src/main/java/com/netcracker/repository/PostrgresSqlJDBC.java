package com.netcracker.repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostrgresSqlJDBC {

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PostrgresSqlJDBC.logger = logger;
    }

    Connection connection;

    private static Logger logger = Logger.getLogger(PostrgresSqlJDBC.class.getName());

    public Connection newConnection() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            //logger.log(Level.WARNING,"PostgreSQL JDBC Driver is not found.");
            System.out.println("PostgreSQL JDBC Driver is not found.");
            e.printStackTrace();
            return null;
        }

        String url = "jdbc:postgresql://localhost:15432/netcracker";
        String user = "postgres";
        String pass = "28y75w";
        this.connection = DriverManager.getConnection(url, user, pass);
        return connection;

    }

    public ResultSet getAllData () throws SQLException{
        Statement statement=this.connection.createStatement();
        String query="select * from client_contract";
        ResultSet resultSet=statement.executeQuery(query);
        return resultSet;
    }

    public void closeConnection () throws SQLException{
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
