package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.*;
import com.queasy.model.quiz.Question;
import com.queasy.model.quiz.Quiz;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;
import com.queasy.utility.enums.QuestionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuizDaoImpl implements QuizDao {

    private final ConnectionPool connectionPool;
    public QuizDaoImpl(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    private Quiz getQuizHelper(Connection con, String query)
    {
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            if(res.next()) {
                return new Quiz(res.getInt(MyConstants.ID),
                                res.getString(MyConstants.QuizDatabaseConstants.QUIZ_NAME),
                                res.getInt(MyConstants.QuizDatabaseConstants.CREATOR_ID),
                                res.getString(MyConstants.QuizDatabaseConstants.DESCRIPTION),
                                getAllQuestions(res.getInt(MyConstants.ID)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private List<Quiz> getQuizHelperList(Connection con, String query)
    {
        List<Quiz> quizzes = new ArrayList();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                List<Question> questions = getAllQuestions(res.getInt(MyConstants.ID));
                quizzes.add(new Quiz(res.getInt(MyConstants.ID),
                        res.getString(MyConstants.QuizDatabaseConstants.QUIZ_NAME),
                        res.getInt(MyConstants.QuizDatabaseConstants.CREATOR_ID),
                        res.getString(MyConstants.QuizDatabaseConstants.DESCRIPTION),
                        questions));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    @Override
    public List<Question> getAllQuestions(int quizId) {
        QuizQuestionDao quizQuestionDao = new QuizQuestionDaoImpl(connectionPool);
        return quizQuestionDao.getQuestionsOf(quizId);
    }

    @Override
    public String getDescription(int quizId) {
        Quiz quiz = getQuiz(quizId);
        return (quiz != null) ? quiz.getDescription() : MyConstants.emptyString;
    }

    @Override
    public String getQuizName(int quizId) {
        Quiz quiz = getQuiz(quizId);
        return (quiz != null) ? quiz.getQuizName() : MyConstants.emptyString;
    }

    @Override
    public User getCreator(int quizId) {
        Quiz quiz = getQuiz(quizId);
        if (quiz == null)
            return null;
        Connection con = connectionPool.acquireConnection();
        String[] columns = {};
        String condition = MyConstants.ID + " = " + Integer.toString(quiz.getCreatorId());
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(StaticMethods.selectQuery(MyConstants.USERS_DATABASE,columns,condition));
            if(res.next()) {
                return new User(res.getInt(MyConstants.ID),
                        res.getString(MyConstants.USER_NAME),
                        res.getString(MyConstants.USER_MAIL),
                        res.getString(MyConstants.USER_PASSWORD));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);
        return null;
    }

    @Override
    public Quiz getQuiz(int quizId) {
        Connection con = connectionPool.acquireConnection();
        String[] columns = {};
        String condition = MyConstants.ID + " = " + Integer.toString(quizId);

        Quiz quiz = getQuizHelper(con, StaticMethods.selectQuery(MyConstants.QuizDatabaseConstants.DATABASE,columns,condition));
        connectionPool.releaseConnection(con);
        return quiz;
    }

    @Override
    public Quiz getQuiz(String quizName) {
        Connection con = connectionPool.acquireConnection();
        String[] columns = {};
        String condition = MyConstants.QuizDatabaseConstants.QUIZ_NAME + " = " + StaticMethods.apostropheString(quizName);

        Quiz quiz = getQuizHelper(con, StaticMethods.selectQuery(MyConstants.QuizDatabaseConstants.DATABASE,columns,condition));
        connectionPool.releaseConnection(con);
        return quiz;
    }

    //TODO:
    @Override
    public boolean addQuiz(Quiz quiz) {
        String query = "INSERT INTO " + MyConstants.QuizDatabaseConstants.DATABASE + " ( " +
                        MyConstants.QuizDatabaseConstants.QUIZ_NAME + " , " +
                        MyConstants.QuizDatabaseConstants.CREATOR_ID +  " , " +
                        MyConstants.QuizDatabaseConstants.DESCRIPTION + " ) " +
                        " VALUES ( " + StaticMethods.apostropheString(quiz.getQuizName()) + " , " +
                        Integer.toString(quiz.getCreatorId()) + " , " +
                        StaticMethods.apostropheString(quiz.getDescription())  + " ) ;";
        boolean answer = true;
        List<Question> questions = quiz.getQuestions();
        QuestionDao questionDao = new QuestionDaoImpl(connectionPool);
        for (int i = 0; i < questions.size(); i++ )  {
            answer = answer && questions.add(questions.get(i));
        }
        return answer && StaticMethods.executeUpdateQuery(connectionPool,query);
    }

    @Override
    public boolean removeQuiz(int quizId) {
        String query = "DELETE FROM " + MyConstants.QuizDatabaseConstants.DATABASE + " WHERE " +
                        MyConstants.ID + " = " + quizId + " ;";
        return StaticMethods.executeUpdateQuery(connectionPool,query);

    }

    @Override
    public List<Quiz> getAllQuizzes() {
        Connection con = connectionPool.acquireConnection();
        String[] columns = {};

        List<Quiz> quizzes = getQuizHelperList(con,
                StaticMethods.selectQuery(MyConstants.QuizDatabaseConstants.DATABASE,
                                          columns,
                                 ""));
        connectionPool.releaseConnection(con);
        return quizzes;
    }
}
