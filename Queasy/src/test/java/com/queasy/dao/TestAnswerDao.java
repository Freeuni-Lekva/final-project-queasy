package com.queasy.dao;

import com.queasy.dao.implementation.AnswerDaoImpl;
import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.interfaces.AnswerDao;
import com.queasy.dao.interfaces.ConnectionPool;
import org.junit.Before;
import org.junit.Test;

public class TestAnswerDao {
    private static ConnectionPool connectionPool;
    private static AnswerDao answerDao;
    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(4);
        answerDao = new AnswerDaoImpl(connectionPool);
    }

    @Test
    public void testGetAllAnswersOf() {

    }

}
