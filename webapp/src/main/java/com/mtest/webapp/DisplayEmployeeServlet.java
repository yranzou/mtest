package com.mtest.webapp;

import com.mtest.dao.EmployeeDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *  Created by yuri on 30.11.17.
 */
public class DisplayEmployeeServlet extends HttpServlet {
    private static long serialVersionID = 1L;
    private EmployeeDao employeeDao = new EmployeeDao();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List employees = this.employeeDao.getAll();
        StringBuilder sb = new StringBuilder("<html><head><title>Employees</title></head><body><table>");
//        for (Employee emmployee:employees
//             ) {
//            sb.append("<tr><td>");
//            sb.append(emmployee.getId());
//            sb.append("</td><td>");
//            sb.append(emmployee.getName());
//            sb.append("</td><td>");
//            sb.append(emmployee.getSurname());
//            sb.append("</td></tr>");
//        }
        sb.append("</table></body></html>");
        resp.getOutputStream().write(sb.toString().getBytes());
//        resp.getOutputStream().write(.getBytes());
    }
}
