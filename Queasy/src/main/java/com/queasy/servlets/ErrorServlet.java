package com.queasy.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Error")
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req,resp);
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req
                .getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = (Integer) req
                .getAttribute("jakarta.servlet.error.status_code");
        String servletName = (String) req
                .getAttribute("jakarta.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) req
                .getAttribute("jakarta.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        RequestDispatcher rd;
        rd = req.getRequestDispatcher("/error/400-499.jsp");
        rd.forward(req,resp);
        if(statusCode >= 400 && statusCode < 500) {

        } else if (statusCode >= 500 && statusCode < 600){
//            rd = req.getRequestDispatcher();
        } else {
//            rd = req.getRequestDispatcher();
        }

    }
}
