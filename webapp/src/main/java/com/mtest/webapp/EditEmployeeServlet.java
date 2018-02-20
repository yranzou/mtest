package com.mtest.webapp;

import com.mtest.model.Employee;
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
public class EditEmployeeServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    private EmployeeService employeeService = new EmployeeService();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surName = req.getParameter("surname");
        String phone = req.getParameter("phone");
        int id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeService.get(id);
        employee.setName(name);
        employee.setSurname(surName);
        employee.setPhone(phone);
        employeeService.update(employee);
//        resp.sendRedirect("/displayEmployees");
        resp.sendRedirect(resp.encodeRedirectURL("displayEmployees"));
    }
}