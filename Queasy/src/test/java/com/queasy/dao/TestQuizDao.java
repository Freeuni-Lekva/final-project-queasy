package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.GameDaoImpl;
import com.queasy.dao.implementation.QuizDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.GameDao;
import com.queasy.dao.interfaces.QuizDao;
import org.junit.Before;

public class TestQuizDao {
    private static ConnectionPool connectionPool;
    private static QuizDao quizDao;
    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30);
        quizDao = new QuizDaoImpl(connectionPool);
    }
}
