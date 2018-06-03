package com.mtest.webapp;

import com.mtest.server.common.DepartmentService;
import com.mtest.server.common.EmployeeService;
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
public class DisplayEmployeeServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    @Autowired
    private EmployeeService employeeService;// = new EmployeeService();
    @Autowired
    private DepartmentService departmentService;// = new DepartmentService();
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
        this.applicationContext.getBean(EmployeeService.class);
        this.applicationContext.getBean(DepartmentService.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("departments",  departmentService.search("NAME",""));
        req.setAttribute("employees",  employeeService.search("NAME", ""));
        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(req,resp);
    }
}
