package com.queasy.servlets;

import com.queasy.dao.interfaces.FollowingDao;
import com.queasy.dao.interfaces.UserDao;
import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.enums.RequestType;
import com.queasy.utility.security.SessionManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UserDetailsServlet")
public class UserDetailsServlet extends HttpServlet {
    //TODO: ეს გასაწერია რომ რექვესთი ჯსტლ-შიც გაუშვას, და ასევე გასაწერია რომ ჰოთლინკები
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        ServletContext context = req.getServletContext();
        String currentFriendRequest = req.getParameter("request");
        int id = Integer.parseInt(req.getParameter("id"));
        String myPage = req.getParameter("myPage");
        UserDao userDao = (UserDao) context.getAttribute(MyConstants.ContextAttributes.USER_DAO);
        FollowingDao followingDao = (FollowingDao) context.getAttribute(MyConstants.ContextAttributes.FOLLOWING_DAO);
        User user = userDao.getUser(id);
        User myUser = SessionManager.getUser(req);
        boolean friendRequest = currentFriendRequest != null;
        //reqvestis gagzavna washla da egeni
        if(friendRequest) {
            if(RequestType.ACCEPT_REQUEST.toString().equals(currentFriendRequest)) {
                followingDao.sendRequest(myUser.getUserName(),user.getUserName());
            } else if (RequestType.SEND_REQUEST.toString().equals(currentFriendRequest)) {
                followingDao.sendRequest(myUser.getUserName(),user.getUserName());
            } else if (RequestType.UNFRIEND.toString().equals(currentFriendRequest)) {
                followingDao.removeRequest(myUser.getUserName(),user.getUserName());
            } else if (RequestType.UNSEND_REQUEST.toString().equals(currentFriendRequest)) {
                followingDao.removeRequest(myUser.getUserName(),user.getUserName());
            } else {

            }
            // to prevent jumping from my page
            if(myPage == null) {
                req.setAttribute("id",Integer.toString(id));
            } else {
                resp.sendRedirect("/profile");
                return;
            }
            rd = req.getRequestDispatcher("/profile");
            rd.forward(req,resp);
        }    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
