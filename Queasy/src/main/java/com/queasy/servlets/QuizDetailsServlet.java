package com.queasy.servlets;

import com.queasy.dao.implementation.*;
import com.queasy.dao.interfaces.*;
import com.queasy.model.game.Game;
import com.queasy.model.quiz.Answer;
import com.queasy.model.quiz.Question;
import com.queasy.model.quiz.Quiz;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.enums.QuestionType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/QuizDetailsServlet")
public class QuizDetailsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        HttpSession session = req.getSession();
        int id = Integer.parseInt(req.getParameter(MyConstants.Servlets.QUIZ_ID));


        System.out.println(id);
        QuizDao quizDao = (QuizDao) context.getAttribute(MyConstants.ContextAttributes.QUIZ_DAO);
        Quiz quiz = quizDao.getQuiz(id);

        AnswerDao answerDao = (AnswerDao) context.getAttribute(MyConstants.ContextAttributes.ANSWER_DAO);
        List<Answer> multipleChoiceAnswers = new ArrayList<>();
        List<Question> questions = quiz.getQuestions().stream().filter(c -> c.getQuestionType() == QuestionType.MULTIPLE_CHOICE).collect(Collectors.toList());
        for(int i = 0; i < questions.size();i++) {
            multipleChoiceAnswers.addAll(answerDao.getAllAnswersOf(questions.get(i).getId()));
        }

        RequestDispatcher rd;
        req.setAttribute("id",quiz.getId());
        rd = req.getRequestDispatcher("/quiz/quizDetails.jsp");

        rd.forward(req,resp);
    }
}
