package com.queasy.dao;

import com.queasy.dao.implementation.AnswerPicturesDaoImpl;
import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.interfaces.AnswerPicturesDao;
import com.queasy.dao.interfaces.ConnectionPool;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

public class TestAnswerPicturesDao {
    private static ConnectionPool connectionPool;
    private static AnswerPicturesDao answerPicturesDao;

    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(4);
        answerPicturesDao = new AnswerPicturesDaoImpl(connectionPool);
    }

    @Test
    public void testGetPicturesOfAnswer() {
        Assert.assertTrue(answerPicturesDao.getPicturesOfAnswer(1).size() == 2);
        Assert.assertTrue(answerPicturesDao.getPicturesOfAnswer(1).get(0).getPicture().equals("https://www.thesprucepets.com/thmb/jwooso-kgF7r5gYS3H3p8fsbTHk=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/GettyImages-135630198-5ba7d225c9e77c0050cff91b.jpg") ||
                answerPicturesDao.getPicturesOfAnswer(1).get(0).getPicture().equals("https://qph.cf2.quoracdn.net/main-qimg-7503bd66ded5565e873c4c2f2ccbc8ad-lq"));
        Assert.assertTrue(answerPicturesDao.getPicturesOfAnswer(1).get(1).getPicture().equals("https://www.thesprucepets.com/thmb/jwooso-kgF7r5gYS3H3p8fsbTHk=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/GettyImages-135630198-5ba7d225c9e77c0050cff91b.jpg") ||
                answerPicturesDao.getPicturesOfAnswer(1).get(1).getPicture().equals("https://qph.cf2.quoracdn.net/main-qimg-7503bd66ded5565e873c4c2f2ccbc8ad-lq"));
        Assert.assertFalse(answerPicturesDao.getPicturesOfAnswer(6).size() == 1);
    }
}
