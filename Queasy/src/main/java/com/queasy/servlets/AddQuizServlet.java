package com.queasy.servlets;

import com.queasy.dao.interfaces.UserDao;
import com.queasy.model.quiz.Quiz;
import com.queasy.utility.constants.MyConstants;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/quiz/createQuiz")
public class AddQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        String username = req.getParameter(MyConstants.Servlets.USERNAME);
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        UserDao userDao = (UserDao) context.getAttribute(MyConstants.ContextAttributes.USER_DAO);
        userDao.getUser(name);
//        Quiz quiz = new Quiz(0,name,,description,new ArrayList<>());
    }
}
