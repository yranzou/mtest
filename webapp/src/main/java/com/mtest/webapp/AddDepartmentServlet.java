package com.mtest.webapp;

import com.mtest.model.Department;
import com.mtest.server.DepartmentService;
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
public class AddDepartmentServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
        this.applicationContext.getBean(DepartmentService.class);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int chiefId = Integer.parseInt(req.getParameter("chiefId"));
        Department department = new Department();
        department.setChiefId(chiefId);
        department.setName(name);
        departmentService.create(department);
        resp.sendRedirect(resp.encodeRedirectURL("displayEmployees"));

    }
}
