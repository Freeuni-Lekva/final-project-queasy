package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.AnswerPicturesDao;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.model.quiz.Pictures;
import com.queasy.utility.constants.MyConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnswerPicturesDaoImpl implements AnswerPicturesDao {
    private final ConnectionPool connectionPool;
    public AnswerPicturesDaoImpl(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Pictures> getPicturesOfAnswer(int answerId) {
        String query = "SELECT * " +
                " FROM " + MyConstants.AnswersPicturesDatabaseConstants.DATABASE +
                " WHERE " + MyConstants.AnswersPicturesDatabaseConstants.ANSWER_ID +
                " = " + Integer.toString(answerId) + ";";
        Connection con = connectionPool.acquireConnection();
        List<Pictures> pictures = new ArrayList();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                pictures.add(new Pictures(res.getInt(MyConstants.ID),
                        res.getString(MyConstants.AnswersPicturesDatabaseConstants.PICTURE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);
        return pictures;
    }
}
