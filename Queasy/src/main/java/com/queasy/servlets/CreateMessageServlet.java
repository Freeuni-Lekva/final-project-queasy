package com.queasy.servlets;

import com.queasy.dao.interfaces.MailDao;
import com.queasy.model.user.Mail;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.security.SessionManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CreateMessageServlet")
public class CreateMessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();

        User currUser = SessionManager.getUser(req);
        MailDao mailDao = (MailDao) context.getAttribute(MyConstants.ContextAttributes.MAIL_DAO);

        String subject = req.getParameter("subject");
        String receiver = req.getParameter("receiver");
        String message = req.getParameter("message");
        subject = (subject == null) ? "" : subject;
        message = (message == null) ? "" : message;
        boolean sent = mailDao.addMail(new Mail(0,subject,message,new java.util.Date(),currUser.getUserName(),receiver));
        if (sent) {
            resp.sendRedirect("/mail/success.jsp");
        } else {
            resp.sendRedirect("/mail/failed.jsp");
        }

    }
}
