package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.QuestionDao;
import com.queasy.model.quiz.Question;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;
import com.queasy.utility.enums.QuestionType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Stack;

public class QuestionDaoImpl implements QuestionDao {

    private final ConnectionPool connectionPool;
    public QuestionDaoImpl(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

//    @Override
//    public List<Question> getAllQuestions() {
//        return null;
//    }
//
//    @Override
//    public List<Question> getAllQuestionsOf(QuestionType qt) {
//        return null;
//    }
//
//    @Override
//    public List<Question> getAllQuestionsOf(int quizId) {
//        return null;
//    }
    private boolean executeUpdating(String query) {
        Connection con = connectionPool.acquireConnection();
        try {
            Statement statement = con.createStatement();
            if(statement.executeUpdate(query) > 0) {
                connectionPool.releaseConnection(con);
                return true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);
        return false;
    }
    @Override
    public boolean addQuestion(Question question) {
        String query = "INSERT INTO " + MyConstants.QuestionsDatabaseConstants.DATABASE + " ( " +
                        MyConstants.QuestionsDatabaseConstants.TEXT + " , " +
                        MyConstants.QuestionsDatabaseConstants.TYPE +
                        " , " + MyConstants.QuestionsDatabaseConstants.CREATOR_ID + " ) " +
                        " VALUES ( '" + question.getText() + "' , '" + question.getQuestionType().toString() +
                        "' , " + Integer.toString(question.getCreatorId());


        return executeUpdating(query);
    }

    @Override
    public boolean removeQuestion(int questionId) {
        String query = "DELETE FROM " + MyConstants.QuestionsDatabaseConstants.DATABASE +
                       "WHERE " + MyConstants.ID + " = " + questionId + " ;";

        return executeUpdating(query);
    }
}
