package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.UserDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.UserDao;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestUserDao extends TestCase {
    private static ConnectionPool connectionPool;
    private static UserDao userDao;
    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(4);
        userDao = new UserDaoImpl(connectionPool);
    }

    public void testAddUsers()
    {

//        assertTrue(userDao.addUser("bachi","123","@fead"));
//        assertTrue(userDao.addUser("levan","123","@feerad"));
        assertFalse(userDao.addUser("bachi","123","@fd"));
        assertFalse(userDao.addUser("bareachi","123","@fead"));

        assertTrue(userDao.getUser("bachi") != null);
    }
}
