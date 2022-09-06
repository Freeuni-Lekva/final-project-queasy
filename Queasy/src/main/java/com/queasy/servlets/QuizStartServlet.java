package com.queasy.servlets;

import com.queasy.dao.interfaces.AnswerDao;
import com.queasy.dao.interfaces.QuizDao;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/QuizServlet")
public class QuizStartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ServletContext context = req.getServletContext();


        int id = Integer.parseInt(req.getParameter(MyConstants.Servlets.QUIZ_ID));
        QuizDao quizDao = (QuizDao) context.getAttribute(MyConstants.ContextAttributes.QUIZ_DAO);
        Quiz quiz = quizDao.getQuiz(id);

        AnswerDao answerDao = (AnswerDao) context.getAttribute(MyConstants.ContextAttributes.ANSWER_DAO);
        List<Answer> multipleChoiceAnswers = new ArrayList<>();
        List<Question> questions = quiz.getQuestions().stream().filter(c -> c.getQuestionType() == QuestionType.MULTIPLE_CHOICE).collect(Collectors.toList());
        for(int i = 0; i < questions.size();i++) {
            multipleChoiceAnswers.addAll(answerDao.getAllAnswersOf(questions.get(i).getId()));
        }

        session.setAttribute(MyConstants.Servlets.MULTIPLE_CHOICE_ANSWER,multipleChoiceAnswers);
        session.setAttribute(MyConstants.Servlets.QUIZ_START_TIME,new java.util.Date());

        req.setAttribute(MyConstants.Servlets.QUIZ_ID,id);

        RequestDispatcher rd;
        rd = req.getRequestDispatcher("/quiz/quizOnePage.jsp");
        rd.forward(req,resp);
    }
}
