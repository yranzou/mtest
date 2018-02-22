package com.mtest.webapp;

import com.mtest.model.Employee;
import com.mtest.server.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *  Created by yuri on 30.11.17.
 */
public class DisplayEmployeeServlet extends HttpServlet {
    private final long serialVersionID = 1L;
//    private EmployeeService employeeService = new EmployeeService();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Employee> employees = new EmployeeService().getAll();
        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(50);
        req.setAttribute("employees",  new EmployeeService().getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(req,resp);
    }
}
