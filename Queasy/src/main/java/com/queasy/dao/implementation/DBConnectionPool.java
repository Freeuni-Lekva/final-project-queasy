package com.queasy.dao.implementation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

import com.queasy.dao.interfaces.ConnectionPool;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DBConnectionPool implements ConnectionPool{
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/queasy_database";
    private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final int MAX_CONNECTIONS = 8;
    private static final int MIN_CONNECTIONS = 5;
    private static BasicDataSource dataSource;
    private static DBConnectionPool connectionPool;

    private ArrayBlockingQueue<Connection> connections;
    private int numberOfConnections;

    private DBConnectionPool(int numberOfConnections) {

        this.numberOfConnections = (numberOfConnections > MAX_CONNECTIONS ) ? MAX_CONNECTIONS : numberOfConnections;
        this.numberOfConnections = (numberOfConnections < MIN_CONNECTIONS ) ? MIN_CONNECTIONS : this.numberOfConnections;

        connections = new ArrayBlockingQueue<>(this.numberOfConnections);
        System.out.println("size = ");
        try{
            for (int i = 0; i < this.numberOfConnections; i++) {
                System.out.println(i);
                Connection con = dataSource.getConnection();
                this.connections.add(con);

            }

        }catch (SQLException e) {
            System.out.println("error message");
            e.printStackTrace();
        }
    }
    public static ConnectionPool getInstance(int numberOfConnections) {
        if (connectionPool == null) {
            connectionPool = new DBConnectionPool(numberOfConnections);
        }
        return connectionPool;

    }

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
            dataSource.setDriverClassName(DB_DRIVER_CLASS);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection acquireConnection() {
        try {

            return this.connections.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void releaseConnection(Connection connection) {
        this.connections.add(connection);
    }
}
