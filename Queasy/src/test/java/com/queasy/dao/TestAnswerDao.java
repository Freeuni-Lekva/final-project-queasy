package com.queasy.dao;

import com.queasy.dao.implementation.AnswerDaoImpl;
import com.queasy.dao.implementation.AnswerPicturesDaoImpl;
import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.interfaces.AnswerDao;
import com.queasy.dao.interfaces.AnswerPicturesDao;
import com.queasy.dao.interfaces.ConnectionPool;

import com.queasy.model.quiz.Answer;
import com.queasy.model.quiz.Picture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import java.util.ArrayList;
import java.util.List;

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
        Assert.assertTrue(answerDao.getAllAnswersOf(1).size() == 2);
        Assert.assertTrue(answerDao.getAllAnswersOf(1).get(0).getText().toLowerCase().contains("some text") );
        Assert.assertTrue(answerDao.getAllRightAnswersOf(1).size() == 1);
       }
    @Test
    public void testAddAnswer() {
        List<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture(0,"111"));
        pictures.add(new Picture(0,"222"));

        Assert.assertTrue(answerDao.addAnswer(new Answer(0,"txet","Y",1,pictures)) == 14);


    }
}
