package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.GameDaoImpl;
import com.queasy.dao.implementation.QuestionDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.GameDao;
import com.queasy.dao.interfaces.QuestionDao;
import com.queasy.model.quiz.Picture;
import com.queasy.model.quiz.Question;
import com.queasy.utility.enums.QuestionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestQuestionDao {
    private static ConnectionPool connectionPool;
    private static QuestionDao questionDao;
    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(30);
        questionDao = new QuestionDaoImpl(connectionPool);
    }
    @Test
    public void testAddQuestion() {
        List<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture(0,"123"));
        pictures.add(new Picture(0,"323"));

        Assert.assertTrue(questionDao.addQuestion(new Question(0,"eee", QuestionType.QUESTION_RESPONSE,1,pictures)));
    }
}
