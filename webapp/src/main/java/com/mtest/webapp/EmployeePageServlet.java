package com.mtest.webapp;

import com.mtest.model.Employee;
import com.mtest.server.common.EmployeeService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Created by yuri on 30.11.17.
 */
public class EmployeePageServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
        this.applicationContext.getBean(EmployeeService.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeService.get(id);
        String image = "data:image/png;base64," + Base64.encode(employee.getPhoto());
        req.setAttribute("employee",  employee);
        req.setAttribute("image",image);
        req.getRequestDispatcher("/WEB-INF/jsp/employeePage.jsp").forward(req,resp);

    }
}
