package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.QuestionPicturesDao;
import com.queasy.model.quiz.Pictures;
import com.queasy.model.quiz.Question;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.enums.QuestionType;

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
    public List<Pictures> getPicturesOfQuestion(int questionId) {
        String query = "SELECT * " +
                       " FROM " + MyConstants.QuestionsPicturesDatabaseConstants.DATABASE +
                       " WHERE " + MyConstants.QuestionsPicturesDatabaseConstants.QUESTION_ID +
                       " = " + Integer.toString(questionId) + ";";
        Connection con = connectionPool.acquireConnection();
        List<Pictures> pictures = new ArrayList();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
               pictures.add(new Pictures(res.getInt(MyConstants.ID),
                                        res.getString(MyConstants.QuestionsPicturesDatabaseConstants.PICTURE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);

        return pictures;
    }
}
