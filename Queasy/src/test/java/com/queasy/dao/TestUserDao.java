package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.UserDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.UserDao;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUserDao  {
    private static ConnectionPool connectionPool;
    private static UserDao userDao;

    private void helper (String query) {
        Connection con = connectionPool.acquireConnection();
        try {
            Statement statement = con.createStatement();
            System.out.println(query);
            if(statement.executeUpdate(query) > 0) {
            }
            connectionPool.releaseConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
        String deleteQuery = StaticMethods.deleteQuery(MyConstants.USERS_DATABASE,
                MyConstants.USER_NAME + " = " + StaticMethods.apostropheString("bachi") + " OR " +
                        MyConstants.USER_NAME + " = " + StaticMethods.apostropheString("levan")  );
        System.out.println(deleteQuery);
        StaticMethods.executeUpdateQuery(connectionPool,deleteQuery);
        helper(deleteQuery);

        //TODO: წაშლა გავაკეთოთ ჯერ

        //doesn't contain yet, should be added
        Assert.assertTrue(userDao.addUser("bachi","123","@fead"));
        Assert.assertTrue(userDao.addUser("levan","123","@feerad"));

        //already contains
        Assert.assertFalse(userDao.addUser("bachi","123","@fd"));
        Assert.assertFalse(userDao.addUser("bareachi","123","@fead"));
        helper(deleteQuery);

        //username has @
        Assert.assertFalse(userDao.addUser("kdoea@","2312","eda32@"));
        //username doesn't have @
        Assert.assertFalse(userDao.addUser("kdoe","2312","eda32"));

    }
    @Test
    public void testGetUsers() {
        //username
        Assert.assertTrue(userDao.getUser("user1") != null);
        Assert.assertTrue(userDao.getUser("user2") != null);
        Assert.assertTrue(userDao.getUser("usere") == null);

        //mail
        Assert.assertTrue(userDao.getUser("mail2@") != null);
        Assert.assertTrue(userDao.getUser("mail2@") != null);
        Assert.assertTrue(userDao.getUser("@") == null);
    }
    @Test
    public void testUpdatePassword() {
        Assert.assertTrue(userDao.updatePassword("user1","user1"));
//        Assert.assertTrue(userDao.getUser("user1").getPassword().equals("user1"));
    }
    @Test
    public void testUpdateEmail() {
        Assert.assertTrue(userDao.updateEmail("user1","user@gmail.com"));
        Assert.assertTrue(userDao.updateEmail("user1","user1@gmail.com"));

//        Assert.assertTrue(userDao.getUser("user1").getMail().equals("user1@gmail.com"));
    }

    @Test
    public void testUpdateUserName() {
        Assert.assertFalse(userDao.updateUserName("user1","user2"));
        Assert.assertTrue(userDao.updateUserName("user1","user"));
//        Assert.assertTrue(userDao.getUser("user") != null);
        Assert.assertTrue(userDao.updateUserName("user","user1"));

    }
    @Test
    public void testGetAllUsers() {
        Assert.assertTrue(userDao.getAllUsers().size() == 3);
    }


}
