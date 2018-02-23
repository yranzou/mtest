package com.mtest.webapp;

import com.mtest.model.Department;
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
public class AddDepartmentServlet extends HttpServlet {
    private final long serialVersionID = 1L;
//    private EmployeeService employeeService = new EmployeeService();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int chiefId = Integer.parseInt(req.getParameter("chiefId"));
        DepartmentService departmentService = new DepartmentService();
        Department department = new Department();
        department.setChiefId(chiefId);
        department.setName(name);
        int newDepartmentId = departmentService.create(department).getId();
//        req.getRequestDispatcher("/WEB-INF/jsp/addDepartment.jsp").forward(req,resp);
        resp.sendRedirect(resp.encodeRedirectURL("departmentPage?id=" + newDepartmentId));

    }
}
