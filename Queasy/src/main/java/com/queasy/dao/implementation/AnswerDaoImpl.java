package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.AnswerDao;
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
        try {
            Connection con = connectionPool.acquireConnection();
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                answers.add(new Answer(res.getInt(MyConstants.ID),
                        res.getString(MyConstants.AnswersDatabaseConstants.TEXT),
                        res.getInt(MyConstants.AnswersDatabaseConstants.QUESTION_ID)));
            }
            connectionPool.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answers;
    }
}
