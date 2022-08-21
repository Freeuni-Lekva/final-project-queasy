package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.UserDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.UserDao;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestUserDao  {
    private static ConnectionPool connectionPool;
    private static UserDao userDao;
    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(4);
        userDao = new UserDaoImpl(connectionPool);
    }

    @AfterAll
    public void endUp() {

    }
    @Test
    public void testAddUsers()
    {
        //doesn't contain yet, should be added
        Assert.assertTrue(userDao.addUser("bachi","123","@fead"));
        Assert.assertTrue(userDao.addUser("levan","123","@feerad"));

        //already contains
        Assert.assertFalse(userDao.addUser("bachi","123","@fd"));
        Assert.assertFalse(userDao.addUser("bareachi","123","@fead"));

        StaticMethods.executeQuery(connectionPool,StaticMethods.deleteQuery(MyConstants.USERS_DATABASE,
                MyConstants.USER_NAME + " = " + StaticMethods.apostropheString("bachi") + " OR " +
                MyConstants.USER_NAME + " = " + StaticMethods.apostropheString("levan") + " OR " ));


        //username has @
        Assert.assertFalse(userDao.addUser("kdoea@","2312","eda32@"));
        //username doesn't have @
        Assert.assertFalse(userDao.addUser("kdoe","2312","eda32"));
    }
    @Test
    public void testGetUsers() {
        //has
        Assert.assertTrue(userDao.getUser("bachi") != null);
        Assert.assertTrue(userDao.getUser("levan") != null);
        Assert.assertTrue(userDao.getUser("chipotle") == null);

        Assert.assertTrue(userDao.getUser("@fead") != null);
        Assert.assertTrue(userDao.getUser("@feerad") != null);
        Assert.assertTrue(userDao.getUser("@rea") == null);
    }

}
