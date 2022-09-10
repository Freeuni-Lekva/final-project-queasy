package com.queasy.servlets;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.QuizDaoImpl;
import com.queasy.dao.implementation.UserDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.QuizDao;
import com.queasy.dao.interfaces.UserDao;
import com.queasy.model.quiz.Quiz;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;
import com.queasy.utility.enums.QuestionType;
import com.queasy.utility.security.SessionManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.mysql.cj.result.Row;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();

        PrintWriter out = resp.getWriter();
        //TODO: email itac unda sheidzlebodes albat registracia
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        RequestDispatcher rd;
        UserDao userDao = (UserDao) context.getAttribute(MyConstants.ContextAttributes.USER_DAO);
        User user = userDao.getUser(username);
        if(username == null) {
            resp.sendRedirect("/incorrect");

        }
        boolean isCorrectUser = (user != null) && user.getPassword().equals(StaticMethods.returnEncryptedPassword(password));
        if (!isCorrectUser ){
            resp.sendRedirect("/incorrect");
        }else{
            SessionManager.createSessionLogin(req,user);
            resp.sendRedirect("/welcome");
        }
    }

}
