package com.queasy.dao;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.QuestionPicturesDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;

import com.queasy.dao.interfaces.QuestionPicturesDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestQuestionsPicturesDao {
    private static ConnectionPool connectionPool;
    private static QuestionPicturesDao questionPicturesDao;

    @Before
    public void setUp() {
        connectionPool = DBConnectionPool.getInstance(4);
        questionPicturesDao = new QuestionPicturesDaoImpl(connectionPool);
    }

    @Test
    public void testGetPicturesOfQuestion() {
        Assert.assertTrue(questionPicturesDao.getPicturesOfQuestion(1).size() == 2);
        Assert.assertTrue(questionPicturesDao.getPicturesOfQuestion(1).get(0).getPicture().equals(
                "https://www.thesprucepets.com/thmb/jwooso-kgF7r5gYS3H3p8fsbTHk=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/GettyImages-135630198-5ba7d225c9e77c0050cff91b.jpg") ||
                questionPicturesDao.getPicturesOfQuestion(1).get(0).getPicture().equals("https://cdn.vox-cdn.com/thumbor/I7I0t87KZ-vf_GSWrH118jwl6d0=/1400x0/filters:no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/23437452/The_Spy_x_Family_Anime_Succeeds_Because_of_Its_Characters_.jpg"));
        Assert.assertTrue(questionPicturesDao.getPicturesOfQuestion(1).get(1).getPicture().equals("https://www.thesprucepets.com/thmb/jwooso-kgF7r5gYS3H3p8fsbTHk=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/GettyImages-135630198-5ba7d225c9e77c0050cff91b.jpg") ||
                questionPicturesDao.getPicturesOfQuestion(1).get(1).getPicture().equals(
                        "https://cdn.vox-cdn.com/thumbor/I7I0t87KZ-vf_GSWrH118jwl6d0=/1400x0/filters:no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/23437452/The_Spy_x_Family_Anime_Succeeds_Because_of_Its_Characters_.jpg"));
    }
}
