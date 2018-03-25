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
import java.util.ArrayList;
import java.util.List;

/**
 *  Created by yuri on 30.11.17.
 */
public class SearchEmployeeServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        List<Object> list = new ArrayList<>();

//        String name = req.getParameter("name");
        String searchIn = req.getParameter("searchIn");
        String searchValue = req.getParameter("searchValue");
//        list.addAll(new EmployeeService().search(searchIn, searchValue));
//        list.addAll(new DepartmentService().search(searchIn, searchValue));
        req.setAttribute("employees",  employeeService.search(searchIn, searchValue));
        req.setAttribute("departments",  departmentService.search(searchIn, searchValue));
//        req.setAttribute("employees",  list);

        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(req,resp);
//
    }
}
