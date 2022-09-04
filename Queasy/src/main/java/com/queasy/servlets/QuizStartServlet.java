package com.queasy.servlets;

import com.queasy.utility.constants.MyConstants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/quiz/quizOnePage")
public class QuizStartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(MyConstants.Servlets.QUIZ_ID));
        req.setAttribute(MyConstants.Servlets.QUIZ_ID,id);
        RequestDispatcher rd;
        rd = req.getRequestDispatcher("/quiz/quizOnePage.jsp");
        rd.forward(req,resp);
    }
}
