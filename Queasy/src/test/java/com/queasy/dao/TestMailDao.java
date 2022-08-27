package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.MailDaoImpl;
import com.queasy.dao.implementation.MailDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.MailDao;
import com.queasy.model.user.Mail;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestMailDao  {
    private static ConnectionPool connectionPool;
    private static MailDao mailDao;

    private boolean helper (String query) {
        Connection con = connectionPool.acquireConnection();
        try {
            Statement statement = con.createStatement();
            System.out.println(query);
            if(statement.executeUpdate(query) > 0) {
                connectionPool.releaseConnection(con);

                return true;
            }
            connectionPool.releaseConnection(con);

        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(4);
        mailDao = new MailDaoImpl(connectionPool);
    }

    @Test
    public void testGetSentMails() {
        Assert.assertTrue(mailDao.getSentMails("user1").get(0).getText().equals("start"));
        Assert.assertTrue(mailDao.getSentMails("user2").get(0).getText().equals("დაწყება"));
    }

    @Test
    public void testGetReceivedMails() {
        Assert.assertTrue(mailDao.getReceivedMails("user2").get(0).getText().equals("start"));
        Assert.assertTrue(mailDao.getReceivedMails("user3").get(0).getText().equals("დაწყება"));
    }

    @Test
    public void testGetMailById() {
        Assert.assertTrue(mailDao.getEmailById(1).getText().equals("start"));

    }

    @Test
    public void testAddMail() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(MyConstants.dateFormat);
        Long millis = formatter.parse("1990-09-01").getTime();
        java.sql.Date sqlDate = new java.sql.Date(millis);
        Assert.assertTrue(mailDao.addMail(new Mail(0,"123","123", sqlDate,"user1","user3")));
        String deleteQuery = StaticMethods.deleteQuery(MyConstants.MAILS_DATABASE,MyConstants.MAILS_SUBJECT + " = " + " '123';");
        helper(deleteQuery);
    }

}
