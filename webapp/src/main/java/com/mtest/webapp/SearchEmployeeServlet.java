package com.mtest.webapp;

import com.mtest.server.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Created by yuri on 30.11.17.
 */
public class SearchEmployeeServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    private EmployeeService employeeService = new EmployeeService();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String searchIn = req.getParameter("searchIn");
        String searchValue = req.getParameter("searchValue");
//        String surName = req.getParameter("surname");
//        resp.sendRedirect("/displayEmployees");
        req.setAttribute("employees",  employeeService.search(searchIn, searchValue));
//        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//        resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//        resp.setDateHeader("Expires", 0); // Proxies.
        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(req,resp);
//
    }
}
