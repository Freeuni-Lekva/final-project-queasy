package com.queasy.dao.interfaces;

import java.sql.Connection;
public interface ConnectionPool{
    Connection acquireConnection();

    void releaseConnection(Connection connection);
}
