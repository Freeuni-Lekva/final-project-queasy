package com.queasy.servlets;

import com.queasy.dao.interfaces.MailDao;
import com.queasy.dao.interfaces.UserDao;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        ServletContext context = req.getServletContext();
        try{
            String name = req.getParameter("search");
            User user = ((UserDao) context.getAttribute(MyConstants.ContextAttributes.USER_DAO)).getUser(name);
            int id = user.getId();
//            req.setAttribute("id",id);
            rd = req.getRequestDispatcher("/profile?id=" + Integer.toString(id));
            rd.forward(req,resp);
        } catch (Exception e) {
            resp.sendRedirect("/welcome");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
