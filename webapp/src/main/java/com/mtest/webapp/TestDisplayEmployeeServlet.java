package com.mtest.webapp;

import com.mtest.dao.EmployeeDao;
import com.mtest.server.DepartmentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Created by yuri on 30.11.17.
 */
public class TestDisplayEmployeeServlet extends HttpServlet {
    private final long serialVersionID = 1L;
//    @Autowired
//    private EmployeeService employeeService = new EmployeeService();
    private EmployeeDao employeeDao = new EmployeeDao();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Employee> employees = new EmployeeService().getAll();

        req.setAttribute("departments",  new DepartmentService().search("NAME",""));

        req.setAttribute("employees",  employeeDao.getAll());
//        req.setAttribute("employees",  employeeService.search("NAME", ""));
        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(req,resp);
    }
}
