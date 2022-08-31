package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.PictureDao;
import com.queasy.dao.interfaces.QuestionDao;
import com.queasy.model.quiz.Picture;
import com.queasy.model.quiz.Question;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;
import com.queasy.utility.enums.QuestionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Stack;

public class QuestionDaoImpl implements QuestionDao {
    private int idSaver = -1;
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
            idSaver = statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            if(idSaver > 0) {
                connectionPool.releaseConnection(con);
                return true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);
        return false;
    }

    private boolean selectHelperQuestion(String query) {
        Connection con = connectionPool.acquireConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            if(res.next()) {
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
        Connection con = connectionPool.acquireConnection();
        boolean answer = true;
        int questionId = 0;
        int pictureId = 0;
        try {
            Statement statement = con.createStatement();

            String query = "INSERT INTO " + MyConstants.QuestionsDatabaseConstants.DATABASE + " ( " +
                            MyConstants.QuestionsDatabaseConstants.TEXT + " , " +
                            MyConstants.QuestionsDatabaseConstants.TYPE +
                            " , " + MyConstants.QuestionsDatabaseConstants.CREATOR_ID + " ) " +
                            " VALUES ( '" + question.getText() + "' , '" +
                            question.getQuestionType().toString() +
                            "' , " + Integer.toString(question.getCreatorId()) + " );";

            answer = answer && (statement.executeUpdate(query) > 0) ;
            if (!answer)
                return answer;

            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID() AS id;");


            if(rs.next()) {
                 questionId = rs.getInt(1);
            }
            else {
                return false;
            }
            List<Picture> pictures = question.getPictures();


            PictureDao pictureDao = new PictureDaoImpl(connectionPool);
            for(int i = 0; i < pictures.size();i++) {


                String insertPicturesQuery =  "INSERT INTO " + MyConstants.PicturesDatabaseConstants.DATABASE + " ( " +
                                        MyConstants.PicturesDatabaseConstants.PICTURE + " ) " +
                                       " VALUES ( " + StaticMethods.apostropheString(pictures.get(i).getPicture()) + " ); ";
                System.out.println(insertPicturesQuery);
                answer = answer  && (statement.executeUpdate(insertPicturesQuery) > 0);
                if (!answer)
                    return answer;
                rs = statement.executeQuery("SELECT LAST_INSERT_ID() AS id;");
                if(rs.next()) {
                    pictureId = rs.getInt(1);
                }
                else {
                    return false;
                }
                System.out.println(" Integer.toString(pictureId) = " + Integer.toString(pictureId));
                System.out.println(" Integer.toString(questionId) = " + Integer.toString(questionId));

                String insertQuestionPicturesQuery  = "INSERT INTO " + MyConstants.QuestionsPicturesDatabaseConstants.DATABASE +
                                                 " ( " + MyConstants.QuestionsPicturesDatabaseConstants.QUESTION_ID + " , " +
                                                 MyConstants.QuestionsPicturesDatabaseConstants.PICTURE_ID + " ) " +
                                                 "VALUES ( " + Integer.toString(questionId) + " , " +
                                                 Integer.toString(pictureId) + " ); ";


                answer = answer  && (statement.executeUpdate(insertQuestionPicturesQuery) > 0);
                if (!answer)
                    return answer;
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        connectionPool.releaseConnection(con);
        return answer;
    }

    public static int selectLastIndexId(ConnectionPool connectionPool) {
        String query = "SELECT LAST_INSERT_ID() AS id;";
        Connection con = connectionPool.acquireConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            if(res.next()) {
                return res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);
        return -1;
    }

    @Override
    public boolean removeQuestion(int questionId) {
        String query = "DELETE FROM " + MyConstants.QuestionsDatabaseConstants.DATABASE +
                       "WHERE " + MyConstants.ID + " = " + questionId + " ;";

        return executeUpdating(query);
    }
}
