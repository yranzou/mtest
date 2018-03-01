package com.mtest.webapp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

//        request.getRequestDispatcher("").include(request, response);

        HttpSession session=request.getSession();
        session.invalidate();
        String pageToForward = request.getContextPath();
        response.sendRedirect(pageToForward);
//        PrintWriter out = response.getWriter();
//        out.println("In dispatcherServlet <BR>");
//
//        RequestDispatcher rd = request.getRequestDispatcher(pageToForward);
//        rd.include(request, response);

    }
}
