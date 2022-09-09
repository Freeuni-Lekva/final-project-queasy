package com.queasy.servlets;

import jakarta.servlet.RequestDispatcher;
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
        try{
            int id = Integer.parseInt(req.getParameter("search"));
            req.setAttribute("id",id);
            rd = req.getRequestDispatcher("/quiz/quizDetails.jsp");
            rd.forward(req,resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect("/welcome");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
