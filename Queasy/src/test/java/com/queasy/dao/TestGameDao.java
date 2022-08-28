package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.FollowingDaoImpl;
import com.queasy.dao.implementation.GameDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.FollowingDao;
import com.queasy.dao.interfaces.GameDao;
import org.junit.Before;

public class TestGameDao {
    private static ConnectionPool connectionPool;
    private static GameDao gameDao;

    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30);
        gameDao = new GameDaoImpl(connectionPool);
    }
}
