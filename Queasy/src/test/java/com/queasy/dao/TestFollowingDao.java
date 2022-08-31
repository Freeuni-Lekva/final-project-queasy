package com.queasy.dao;

import com.queasy.dao.implementation.AnswerDaoImpl;
import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.FollowingDaoImpl;
import com.queasy.dao.interfaces.AnswerDao;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.FollowingDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFollowingDao {
    private static ConnectionPool connectionPool;
    private static FollowingDao followingDao;

    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30);
        followingDao = new FollowingDaoImpl(connectionPool);
    }
    @Test
    public void getFriendsOf() {
        Assert.assertTrue(followingDao.getFriendsOf("user1").get(0).equals("user2"));
        Assert.assertTrue(followingDao.getFriendsOf("user2").get(0).equals("user1"));
        Assert.assertTrue(followingDao.getFriendsOf("user3").size() == 0);

    }
}
