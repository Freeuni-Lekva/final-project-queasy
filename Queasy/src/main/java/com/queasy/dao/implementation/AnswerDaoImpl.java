package com.queasy.dao.implementation;

import com.queasy.dao.interfaces.AnswerDao;
import com.queasy.dao.interfaces.AnswerPicturesDao;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.PictureDao;
import com.queasy.model.quiz.Answer;
import com.queasy.model.quiz.Picture;
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
        String condition = MyConstants.AnswersDatabaseConstants.QUESTION_ID + " = " + Integer.toString(questionId);
        String query = StaticMethods.selectQuery(MyConstants.AnswersDatabaseConstants.DATABASE,
                                                 columns,
                condition);
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

    @Override
    public int addAnswer(Answer answer) {

        Connection con = connectionPool.acquireConnection();
        boolean result = true;
        int answerId = 0;
        int pictureId = 0;
        try {
            Statement statement = con.createStatement();

            String query = "INSERT INTO " + MyConstants.AnswersDatabaseConstants.DATABASE + " ( " +
                    MyConstants.AnswersDatabaseConstants.TEXT + " , " +
                    MyConstants.AnswersDatabaseConstants.IS_RIGHT_ANSWER +
                    " , " + MyConstants.AnswersDatabaseConstants.QUESTION_ID + " ) " +
                    " VALUES ( '" + answer.getText() + "' , '" +
                    answer.getIsRightAnswer() +
                    "' , " + Integer.toString(answer.getQuestionId()) + " );";

            result = result && (statement.executeUpdate(query) > 0) ;
            if (!result)
                return -1;

            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID() AS id;");


            if(rs.next()) {
                answerId = rs.getInt(1);
            }
            else {
                return -1;
            }
            List<Picture> pictures = answer.getPictures();


            PictureDao pictureDao = new PictureDaoImpl(connectionPool);
            System.out.println("pictures.size()");
            System.out.println(pictures.size());
            for(int i = 0; i < pictures.size();i++) {


                String insertPicturesQuery =  "INSERT INTO " + MyConstants.PicturesDatabaseConstants.DATABASE + " ( " +
                        MyConstants.PicturesDatabaseConstants.PICTURE + " ) " +
                        " VALUES ( " + StaticMethods.apostropheString(pictures.get(i).getPicture()) + " ); ";
                System.out.println(insertPicturesQuery);
                result = result  && (statement.executeUpdate(insertPicturesQuery) > 0);
                if (!result)
                    return -1;
                rs = statement.executeQuery("SELECT LAST_INSERT_ID() AS id;");
                if(rs.next()) {
                    pictureId = rs.getInt(1);
                }
                else {
                    return -1;
                }
                System.out.println(" Integer.toString(pictureId) = " + Integer.toString(pictureId));
                System.out.println(" Integer.toString(answerId) = " + Integer.toString(answerId));

                String insertAnswersPicturesQuery  = "INSERT INTO " + MyConstants.AnswersPicturesDatabaseConstants.DATABASE +
                        " ( " + MyConstants.AnswersPicturesDatabaseConstants.ANSWER_ID + " , " +
                        MyConstants.AnswersPicturesDatabaseConstants.PICTURE_ID + " ) " +
                        "VALUES ( " + Integer.toString(answerId) + " , " +
                        Integer.toString(pictureId) + " ); ";

                result = result  && (statement.executeUpdate(insertAnswersPicturesQuery) > 0);
                if (!result)
                    return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(con);
        return answerId;
    }
}
