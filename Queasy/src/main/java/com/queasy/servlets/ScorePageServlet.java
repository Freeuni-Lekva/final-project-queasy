package com.queasy.servlets;

import com.queasy.dao.implementation.AnswerDaoImpl;
import com.queasy.dao.implementation.DBConnectionPool;
import com.queasy.dao.implementation.GameDaoImpl;
import com.queasy.dao.interfaces.AnswerDao;
import com.queasy.dao.interfaces.ConnectionPool;
import com.queasy.dao.interfaces.GameDao;
import com.queasy.dao.interfaces.QuizDao;
import com.queasy.model.game.Game;
import com.queasy.model.quiz.Answer;
import com.queasy.model.quiz.Question;
import com.queasy.model.quiz.Quiz;
import com.queasy.utility.constants.MyConstants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/quiz/ScorePage")
public class ScorePageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        HttpSession session = req.getSession();
        QuizDao quizDao = (QuizDao) context.getAttribute(MyConstants.ContextAttributes.QUIZ_DAO);

        int id = Integer.parseInt(req.getParameter(MyConstants.Servlets.QUIZ_ID));
        Quiz quiz = quizDao.getQuiz(id);
        List<Question> questions = quiz.getQuestions();
        Question currQuestion = null;
        String currAnswer = "";

        AnswerDao answerDao = (AnswerDao) context.getAttribute(MyConstants.ContextAttributes.ANSWER_DAO);
        List<Answer> answers =  null;
        List<Answer> correctAnswers = null;
        int score = 0;
        for(int i = 0; i < questions.size(); i++) {
            currQuestion = questions.get(i);
            currAnswer = req.getParameter(MyConstants.Servlets.QUIZ_ANSWER + Integer.toString(currQuestion.getId()));
            currAnswer = currAnswer != null ? currAnswer : "";
            answers = answerDao.getAllAnswersOf(currQuestion.getId());
            correctAnswers = answers.stream().filter(c -> c.getIsRightAnswer().toLowerCase().equals("y")).collect(Collectors.toList());

            //If question had no correct answers we give player a score
            if(correctAnswers.size() == 0) {
                score++;
            } else {
                String finalCurrAnswer = currAnswer;
                if (correctAnswers.stream().
                        filter(c -> c.getText().toLowerCase().equals(finalCurrAnswer.toLowerCase())).collect(Collectors.toList()).size() > 0)
                    score++;
            }
        }
        System.out.println(score);
        RequestDispatcher rd;
        java.util.Date endDate = new java.util.Date();
        java.util.Date startDate = (java.util.Date)session.getAttribute(MyConstants.Servlets.QUIZ_START_TIME);
        session.setAttribute(MyConstants.Servlets.SCORE,score);
        session.setAttribute(MyConstants.Servlets.QUIZ_END_TIME,endDate);

        GameDao gameDao = (GameDao) context.getAttribute(MyConstants.ContextAttributes.GAME_DAO);
        String username = (String)session.getAttribute(MyConstants.Servlets.USERNAME);
        gameDao.addGame(new Game(0,score,startDate,endDate,username,quiz.getId()));

        rd = req.getRequestDispatcher("/quiz/score.jsp");
        rd.forward(req,resp);
    }
}
