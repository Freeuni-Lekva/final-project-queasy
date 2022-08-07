package com.queasy.dao.implementation;
import java.io.IOException;
import java.sql.Connection;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DBConnectionPool {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/queasy";
    private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final int MAX_CONNECTIONS = 50;
    private static final int MIN_CONNECTIONS = 1;
    private static BasicDataSource dataSource;
    private static DBConnectionPool connectionPool;
    

    public static DataSource getDataSource(){
        return dataSource;
    }
    static{
        try{
            dataSource = new BasicDataSource();
            dataSource.setMaxIdle(MAX_CONNECTIONS);
            dataSource.setMinIdle(MIN_CONNECTIONS);
            dataSource.setUrl(DB_URL);
            dataSource.setUsername(DB_USERNAME);
            dataSource.setPassword(DB_PASSWORD);

        }catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
