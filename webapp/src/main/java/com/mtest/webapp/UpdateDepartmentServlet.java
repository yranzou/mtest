package com.mtest.webapp;

import com.mtest.model.Department;
import com.mtest.server.common.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UpdateDepartmentServlet extends HttpServlet {
    private final long serialVersionID = 1L;

    @Autowired
    DepartmentService departmentService;// = new DepartmentService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int chiefId = Integer.parseInt(req.getParameter("chiefId"));
        int id = Integer.parseInt(req.getParameter("id"));
        Department department = departmentService.get(id);
        department.setName(name);
        department.setChiefId(chiefId);
        departmentService.update(department);
//        resp.sendRedirect("/displayEmployees");
        resp.sendRedirect(resp.encodeRedirectURL("departmentPage?id=" + id));
    }
}
