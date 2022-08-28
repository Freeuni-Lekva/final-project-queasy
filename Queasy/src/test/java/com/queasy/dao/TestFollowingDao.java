package com.queasy.dao;

import com.queasy.dao.implementation.AnswerDaoImpl;
import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.FollowingDaoImpl;
import com.queasy.dao.interfaces.AnswerDao;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.FollowingDao;
import org.junit.Before;

public class TestFollowingDao {
    private static ConnectionPool connectionPool;
    private static FollowingDao followingDao;

    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30);
        followingDao = new FollowingDaoImpl(connectionPool);
    }
}
