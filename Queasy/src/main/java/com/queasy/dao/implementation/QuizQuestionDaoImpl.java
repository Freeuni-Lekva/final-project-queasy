package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.QuestionPicturesDao;
import com.queasy.dao.interfaces.QuizQuestionDao;
import com.queasy.model.quiz.Question;
import com.queasy.model.quiz.Quiz;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.enums.QuestionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestionDaoImpl implements QuizQuestionDao {
    private final ConnectionPool connectionPool;

    public QuizQuestionDaoImpl(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }


    @Override
    public List<Question> getQuestionsOf(int quizId) {
        String query = "SELECT questions.* FROM " + MyConstants.QuizDatabaseConstants.DATABASE + " AS quiz " +
                "INNER JOIN " + MyConstants.QuizQuestionsDatabaseConstants.DATABASE + " AS quiz_questions ON " +
                "quiz." + MyConstants.ID + " = " +
                "quiz_questions." + MyConstants.QuizQuestionsDatabaseConstants.QUIZ_ID +
                " INNER JOIN " + MyConstants.QuestionsDatabaseConstants.DATABASE + " AS questions ON " +
                "quiz_questions." + MyConstants.QuizQuestionsDatabaseConstants.QUESTION_ID + " = questions."  +
                MyConstants.ID + " WHERE quiz." + MyConstants.ID + " = " + Integer.toString(quizId) + ";" ;

        Connection con = connectionPool.acquireConnection();
        QuestionPicturesDao pictures = new QuestionPicturesDaoImpl(connectionPool);

        List<Question> questions = new ArrayList();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()) {
                questions.add(new Question(res.getInt(MyConstants.ID),
                                           res.getString(MyConstants.QuestionsDatabaseConstants.TEXT),
                                           QuestionType.valueOf(res.getString(MyConstants.QuestionsDatabaseConstants.TYPE)),
                                           res.getInt(MyConstants.QuestionsDatabaseConstants.CREATOR_ID),
                                           pictures.getPicturesOfQuestion(res.getInt(MyConstants.ID))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

//    @Override
//    public Quiz getQuizOfQuestion(int questionId) {
//        return null;
//    }
}
