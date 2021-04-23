package com.netcracker.contract;

import com.netcracker.repository.PostrgresSqlJDBC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestPostgressql {

    public static PostrgresSqlJDBC postrgresSqlJDBC;

    @Test
    public void getJdbcConnection() throws SQLException{
        postrgresSqlJDBC=new PostrgresSqlJDBC();
        try(Connection connection=postrgresSqlJDBC.newConnection()){
            assertTrue(connection.isValid(1));
        }
    }

    @Before
    public void init() throws SQLException{
        postrgresSqlJDBC=(PostrgresSqlJDBC) postrgresSqlJDBC.newConnection();
    }

    @After
    public void close() throws SQLException{
        postrgresSqlJDBC.getConnection().close();
    }

}
