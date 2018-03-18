package com.mtest.webapp;

import com.mtest.dao.EmployeeDao;
import com.mtest.model.Department;
import com.mtest.model.Employee;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
//        List<Employee> employees = new EmployeeService().getAll();

        req.setAttribute("departments",  departmentService.search("NAME",""));

        // Set standard HTTP/1.1 no-cache headers.
        resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");


        resp.setHeader("Expires", "-1");
// Set standard HTTP/1.0 no-cache header.
        resp.setHeader("Pragma", "no-cache");
//        req.setAttribute("employees",  employeeDao.search("NAME", ""));
        req.setAttribute("employees",  employeeService.search("NAME", ""));
        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(req,resp);
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.print(employeeDao.search("NAME", ""));
    }
}
