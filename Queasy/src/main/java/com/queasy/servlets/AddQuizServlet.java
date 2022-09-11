package com.queasy.servlets;

import com.queasy.dao.interfaces.*;
import com.queasy.model.quiz.Answer;
import com.queasy.model.quiz.Picture;
import com.queasy.model.quiz.Question;
import com.queasy.model.quiz.Quiz;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.enums.QuestionType;
import com.queasy.utility.security.SessionManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/CreateQuizServlet")
public class AddQuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        QuizDao quizDao = (QuizDao) context.getAttribute(MyConstants.ContextAttributes.QUIZ_DAO);
        AnswerDao answerDao = (AnswerDao) context.getAttribute(MyConstants.ContextAttributes.ANSWER_DAO);
        QuizQuestionDao quizQuestionDao = (QuizQuestionDao) context.getAttribute(MyConstants.ContextAttributes.QUIZ_QUESTION_DAO);
        QuestionDao questionDao = (QuestionDao) context.getAttribute(MyConstants.ContextAttributes.QUESTION_DAO);

        User currUser = SessionManager.getUser(req);
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        if(name == null || quizDao.getQuiz(name) != null) {
            resp.sendRedirect("/quiz/failed.jsp");
        }

        System.out.println("name");
        System.out.println(name);
        System.out.println("description");
        System.out.println(description);
        if(name == null || quizDao.getQuiz(name) != null )
        {
            resp.sendRedirect("/quiz/failed.jsp");
            return;
        }
        int quizId = quizDao.addQuiz(new Quiz(0,name,currUser.getId(),description,new ArrayList<>()));
        List<Question> questions = new ArrayList<>();
        if( quizId < 0) {
            resp.sendRedirect("/quiz/failed.jsp");
            return;
        }
        int i =0;
        while(true) {
            String questionText = req.getParameter("questionText-" + Integer.toString(i));
            Picture picture = new Picture(0, req.getParameter("picture-" + Integer.toString(i)));
            String questionTypeString = req.getParameter("question-radio-" + Integer.toString(i));
            QuestionType questionType;
            if(questionTypeString == null) {
                questionType = QuestionType.QUESTION_RESPONSE;
            }
            else if(questionTypeString.equals(QuestionType.MULTIPLE_CHOICE.toString())) {
                questionType = QuestionType.MULTIPLE_CHOICE;
            } else if(questionTypeString.equals(QuestionType.QUESTION_RESPONSE.toString())) {
                questionType = QuestionType.QUESTION_RESPONSE;
            } else if(questionTypeString.equals(QuestionType.PICTURE_RESPONSE.toString())) {
                questionType = QuestionType.PICTURE_RESPONSE;
            } else if(questionTypeString.equals(QuestionType.FILL_IN_THE_BLANK.toString())) {
                questionType = QuestionType.FILL_IN_THE_BLANK;
            } else {
                //by default let it be question response
                questionType = QuestionType.QUESTION_RESPONSE;
            }

            List<Picture> pictures = new ArrayList<>();
            if(req.getParameter("picture-" + Integer.toString(i)) != null)
                pictures.add(picture);
            if(questionText == null)
                break;
            questions.add(new Question(0,questionText,questionType,currUser.getId(),pictures));
            int questionId = questionDao.addQuestion(new Question(0,questionText,questionType,currUser.getId(),pictures));
            quizQuestionDao.addQuizQuestionBonding(quizId,questionId);

            List<Answer> answers = new ArrayList<>();
            int j = 0;
            while(true) {
                String answerText = req.getParameter("answerInput-" + Integer.toString(i) + "-" + Integer.toString(j));
                String answerRadio =  req.getParameter("answerRadio-" + Integer.toString(i) + "-" + Integer.toString(j));
                if(answerText == null)
                    break;
                answers.add(new Answer(0,answerText,answerRadio,questionId,new ArrayList<>()));
                j++;
            }
            for(int k = 0; k < answers.size(); k++) {
                answerDao.addAnswer(answers.get(k));
            }
            i++;
        }
        resp.sendRedirect("/quiz/done.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
