package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.GameDaoImpl;
import com.queasy.dao.implementation.QuizDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.GameDao;
import com.queasy.dao.interfaces.QuizDao;
import com.queasy.model.quiz.Question;
import com.queasy.model.quiz.Quiz;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestQuizDao {
    private static ConnectionPool connectionPool;
    private static QuizDao quizDao;
    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30);
        quizDao = new QuizDaoImpl(connectionPool);
    }

    @Test
    public void testAddQuiz() {
       Assert.assertTrue(quizDao.addQuiz(new Quiz(0,"2214ss144wsa",1,"ddd", new ArrayList< Question >())) == 10);
//       Assert.assertTrue(quizDao.removeQuiz(2));
    }
    @Test
    public void testRemoveQuiz() {
        Assert.assertTrue(quizDao.removeQuiz(1));
    }

    @Test
    public void testGetQuiz() {
        Assert.assertTrue(quizDao.getQuiz(4).getQuizName().equals("QUIZ123"));
        Assert.assertTrue(quizDao.getQuiz(4).getDescription().equals("ddd"));

    }
    @Test
    public void testGetQuizName() {
        Assert.assertTrue(quizDao.getQuizName(4).equals("QUIZ123"));
    }
    @Test
    public void testGetDescription() {
        Assert.assertTrue(quizDao.getDescription(4).equals("ddd"));
    }

    @Test
    public void testGetCreator() {
        Assert.assertTrue(quizDao.getCreator(4).getUserName().equals("user1"));
        Assert.assertTrue(quizDao.getCreator(4).getMail().equals("mail1@"));
        Assert.assertTrue(quizDao.getCreator(4).getPassword().equals("password"));

    }
}
