package com.mtest.webapp;

import com.mtest.server.DepartmentService;
import com.mtest.server.EmployeeService;
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
public class DepartmentPageServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
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
        int id = Integer.parseInt(req.getParameter("id"));
//        HttpSession session = req.getSession(true);
//        session.setMaxInactiveInterval(50);
        req.setAttribute("department",  departmentService.get(id));
        req.setAttribute("departmentChief",  employeeService.getDepartmentChief(id));
        req.getRequestDispatcher("/WEB-INF/jsp/departmentPage.jsp").forward(req,resp);
    }
}
