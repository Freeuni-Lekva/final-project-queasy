package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.AnswerDao;
import com.queasy.dao.interfaces.AnswerPicturesDao;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.model.quiz.Answer;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerDaoImpl implements AnswerDao {

    private ConnectionPool connectionPool;

    public AnswerDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Answer> getAllAnswersOf(int questionId) {
        String[] columns = {};
        String query = StaticMethods.selectQuery(MyConstants.AnswersDatabaseConstants.DATABASE,
                                                 columns,
                                                 MyConstants.emptyString);
        List<Answer> answers = new ArrayList();
        Connection con = connectionPool.acquireConnection();
        try {
            AnswerPicturesDao pictures = new AnswerPicturesDaoImpl(connectionPool);
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                int id = res.getInt(MyConstants.ID);
                answers.add(new Answer(id,
                        res.getString(MyConstants.AnswersDatabaseConstants.TEXT),
                        res.getString(MyConstants.AnswersDatabaseConstants.IS_RIGHT_ANSWER),
                        res.getInt(MyConstants.AnswersDatabaseConstants.QUESTION_ID),
                        pictures.getPicturesOfAnswer(id)));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);
        return answers;
    }

    @Override
    public List<Answer> getAllRightAnswersOf(int questionId) {
        return getAllAnswersOf(questionId).stream().filter(curr -> curr.getIsRightAnswer().toLowerCase().equals("y")).collect(Collectors.toList());
    }
}
