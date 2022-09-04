package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.PictureDao;
import com.queasy.dao.interfaces.QuestionPicturesDao;
import com.queasy.model.quiz.Picture;
import com.queasy.utility.constants.MyConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionPicturesDaoImpl implements QuestionPicturesDao {

    private final ConnectionPool connectionPool;
    public QuestionPicturesDaoImpl(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Picture> getPicturesOfQuestion(int questionId) {
        String query = "SELECT * " +
                       " FROM " + MyConstants.QuestionsPicturesDatabaseConstants.DATABASE +
                       " WHERE " + MyConstants.QuestionsPicturesDatabaseConstants.QUESTION_ID +
                       " = " + Integer.toString(questionId) + ";";
        Connection con = connectionPool.acquireConnection();
        List<Picture> pictures = new ArrayList();
        PictureDao pictureDao = new PictureDaoImpl(connectionPool);
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                int id = res.getInt(MyConstants.QuestionsPicturesDatabaseConstants.PICTURE_ID);
               pictures.add(pictureDao.getPictureOf(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);

        return pictures;
    }
}
