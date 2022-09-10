package com.queasy.servlets;

import com.queasy.dao.interfaces.QuizDao;
import com.queasy.model.quiz.Quiz;
import com.queasy.utility.constants.MyConstants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/QuizSearchServlet")
public class QuizSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        ServletContext context = req.getServletContext();
        QuizDao quizDao = (QuizDao)context.getAttribute(MyConstants.ContextAttributes.QUIZ_DAO);

        try{
            int id = Integer.parseInt(req.getParameter("search"));
            if(quizDao.getQuiz(id) != null) {
                req.setAttribute("id",id);
                rd = req.getRequestDispatcher("/quiz/quizDetails.jsp");
                rd.forward(req,resp);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Quiz quiz = quizDao.getQuiz(req.getParameter("search"));
        if(quiz != null) {
            req.setAttribute("id",quiz.getId());
            rd = req.getRequestDispatcher("/quiz/quizDetails.jsp");
            rd.forward(req,resp);
        }
        resp.sendRedirect("/welcome");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
