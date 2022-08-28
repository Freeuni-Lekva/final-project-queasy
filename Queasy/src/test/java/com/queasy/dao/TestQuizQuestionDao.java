package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.GameDaoImpl;
import com.queasy.dao.implementation.QuizQuestionDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.GameDao;
import com.queasy.dao.interfaces.QuizQuestionDao;
import org.junit.Before;

public class TestQuizQuestionDao {
    private static ConnectionPool connectionPool;
    private static QuizQuestionDao quizQuestionDao;
    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30)
        quizQuestionDao = new QuizQuestionDaoImpl(connectionPool);
    }
}
