package com.queasy.servlets;

import com.queasy.dao.implementation.*;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.enums.QuestionType;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

//TODO: sessionCreated უნდა გადაიხედოს
@WebListener
public class SessionContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    private ConnectionPool connectionPool;
    private User user;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        connectionPool = DBConnectionPool.getInstance(10);

        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.ANSWER_DAO,new AnswerDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.ANSWER_PICTURES_DAO,new AnswerPicturesDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.FOLLOWING_DAO,new FollowingDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.GAME_DAO,new GameDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.MAIL_DAO,new MailDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.PICTURE_DAO,new PictureDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.QUESTION_DAO,new QuestionDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.QUESTION_PICTURES_DAO,new QuestionPicturesDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.QUIZ_DAO,new QuizDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.QUIZ_QUESTION_DAO,new QuizQuestionDaoImpl(connectionPool));
        sce.getServletContext().setAttribute(MyConstants.ContextAttributes.USER_DAO,new UserDaoImpl(connectionPool));

        sce.getServletContext().setAttribute("MILLISECONDS_IN_DAY",MyConstants.Servlets.MILLISECONDS_IN_DAY);
        sce.getServletContext().setAttribute(MyConstants.Servlets.QUESTION_RESPONSE, QuestionType.QUESTION_RESPONSE.name());
        sce.getServletContext().setAttribute(MyConstants.Servlets.MULTIPLE_CHOICE, QuestionType.MULTIPLE_CHOICE.name());
        sce.getServletContext().setAttribute(MyConstants.Servlets.FILL_IN_THE_BLANK, QuestionType.FILL_IN_THE_BLANK.name());
        sce.getServletContext().setAttribute(MyConstants.Servlets.PICTURE_RESPONSE, QuestionType.PICTURE_RESPONSE.name());
        sce.getServletContext().setAttribute("FILL_BLANK_STRING",MyConstants.Servlets.FILL_BLANK_STRING);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        user = null;
        se.getSession().setAttribute(MyConstants.Servlets.USERNAME,user);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
       user = null;
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
     }
}
