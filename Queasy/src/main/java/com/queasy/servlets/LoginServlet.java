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

//i have no idea how to test servlets...
@WebServlet("/login/LoginServlet")
public class LoginServlet extends HttpServlet{
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

        boolean isCorrectUser = (user != null) && user.getPassword().equals(StaticMethods.returnEncryptedPassword(password));
        if (!isCorrectUser ){

            rd = req.getRequestDispatcher("incorrectInfo.jsp");
            rd.forward(req,resp);
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("username",username);

            req.setAttribute("name",username);
            QuizDao quizDao = (QuizDao) context.getAttribute(MyConstants.ContextAttributes.QUIZ_DAO);
            List<Quiz> quizzes = quizDao.getAllQuizzes();

            session.setAttribute(MyConstants.Servlets.QUIZZES,quizzes);

            /*  amas sxvagan gavitan mere */
            session.setAttribute(MyConstants.Servlets.QUESTION_RESPONSE, QuestionType.QUESTION_RESPONSE.name());
            session.setAttribute(MyConstants.Servlets.MULTIPLE_CHOICE, QuestionType.MULTIPLE_CHOICE.name());
            session.setAttribute(MyConstants.Servlets.FILL_IN_THE_BLANK, QuestionType.FILL_IN_THE_BLANK.name());
            session.setAttribute(MyConstants.Servlets.PICTURE_RESPONSE, QuestionType.PICTURE_RESPONSE.name());


            rd = req.getRequestDispatcher("/main/welcome.jsp");
            rd.forward(req,resp);
        }
    }

}
