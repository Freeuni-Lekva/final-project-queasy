package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.GameDaoImpl;
import com.queasy.dao.implementation.QuestionDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.GameDao;
import com.queasy.dao.interfaces.QuestionDao;
import org.junit.Before;

public class TestQuestionDao {
    private static ConnectionPool connectionPool;
    private static QuestionDao questionDao;
    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30)
        questionDao = new QuestionDaoImpl(connectionPool);
    }
}
