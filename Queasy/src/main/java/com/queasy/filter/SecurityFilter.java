package com.queasy.filter;


import com.queasy.utility.constants.MyConstants;
import com.queasy.utility.security.SessionManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//TODO: dasamatebeli iqneba aq...

@WebFilter(urlPatterns = {"/*"})
//        {"/welcome","/score","/create","/quizOnePage","/quizDetails","/profile",
//         "/ScorePageServlet","/CreateQuizServlet","/HomePageServlet","/QuizDetailsServlet","/QuizServlet","/ScorePageServlet",
//        "/main/header.jsp","/main/welcome.jsp","/quiz/createQuiz.jsp","/quiz/quizDetails.jsp","/quiz/quizOnePage.jsp",
//                "/quiz/score.jsp","/user/userDetails.jsp"
//        })
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

            if (req instanceof HttpServletRequest) {
                String url = ((HttpServletRequest)req).getRequestURL().toString();

                if(!MyConstants.Servlets.NOT_LOGGED_URLS.stream().
                        filter(c -> url.endsWith(c)).collect(Collectors.toList()).isEmpty()) {
                    System.out.println("url = "  + url);
                    chain.doFilter(req, resp);
                } else {
                    if(!SessionManager.isLogined(req)) {
                        req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
                    }
                }
               return;
            }
    }

    @Override
    public void destroy() {
    }
}
