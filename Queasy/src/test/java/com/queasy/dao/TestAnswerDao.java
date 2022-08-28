package com.queasy.dao;

import com.queasy.dao.implementation.AnswerDaoImpl;
import com.queasy.dao.implementation.AnswerPicturesDaoImpl;
import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.interfaces.AnswerDao;
import com.queasy.dao.interfaces.AnswerPicturesDao;
import com.queasy.dao.interfaces.ConnectionPool;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

public class TestAnswerDao {
    private static ConnectionPool connectionPool;
    private static AnswerDao answerDao;

    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30);
        answerDao = new AnswerDaoImpl(connectionPool);
    }

    @Test
    public void testGetAllAnswers() {
        Assert.assertTrue(answerDao.getAllAnswersOf(1).size() == 3);
        Assert.assertTrue(answerDao.getAllAnswersOf(1).get(0).getText().toLowerCase().contains("some text") );
       }
}
