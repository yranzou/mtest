package com.mtest.webapp;

import com.mtest.model.Employee;
import com.mtest.server.EmployeeService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *  Created by yuri on 30.11.17.
 */
public class EmployeePageServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    private EmployeeService employeeService = new EmployeeService();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Employee> employees = new EmployeeService().getAll();
        int id = Integer.parseInt(req.getParameter("id"));
//        HttpSession session = req.getSession(true);
//        session.setMaxInactiveInterval(50);
        Employee employee = employeeService.get(id);
        String image = "data:image/png;base64," + Base64.encode(employee.getPhoto());
        req.setAttribute("employee",  employee);
        req.setAttribute("image",image);
        req.getRequestDispatcher("/WEB-INF/jsp/employeePage.jsp").forward(req,resp);

    }
}
