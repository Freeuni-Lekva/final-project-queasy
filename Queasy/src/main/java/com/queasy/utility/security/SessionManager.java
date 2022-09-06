package com.queasy.utility.security;

import com.queasy.model.user.User;
import com.queasy.utility.constants.MyConstants;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionManager {

    public static boolean isLogined(ServletRequest req) {

        HttpSession session = ((HttpServletRequest) req).getSession();

        User user = (User) session.getAttribute(MyConstants.Servlets.LOGINED_USER);

        return user != null;
    }

    public static void createSessionLogin(HttpServletRequest req, User user) {

        HttpSession oldSession = req.getSession(false);
        if(oldSession != null) oldSession.invalidate();

        HttpSession newSession = req.getSession(true);
        newSession.setMaxInactiveInterval(MyConstants.Servlets.MAX_INACTIVE_TIME);
        newSession.setAttribute(MyConstants.Servlets.LOGINED_USER, user);
    }

    public static User getUser(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute(MyConstants.Servlets.LOGINED_USER);

        if(user == null) throw new RuntimeException("No session user");

        return user;
    }
}
