package com.queasy.servlets;

import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.UserDaoImpl;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.UserDao;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.constants.StaticMethods;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//i have no idea how to test servlets...

@WebServlet("/AddAccountServlet")
public class AddAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        String username = req.getParameter("username");
        String mail = req.getParameter("email");
        String password = req.getParameter("password");

        UserDao userDao =  (UserDao) context.getAttribute(MyConstants.ContextAttributes.USER_DAO);
        boolean isCorrect = userDao.addUser(username, StaticMethods.returnEncryptedPassword(password),mail);

        if (!isCorrect){
            resp.sendRedirect("/creationFailed");

        }else{
            resp.sendRedirect("/");
        }
    }
}
