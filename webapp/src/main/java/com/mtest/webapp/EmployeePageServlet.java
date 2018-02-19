package com.mtest.webapp;

import com.mtest.server.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Created by yuri on 30.11.17.
 */
public class EmployeePageServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    private EmployeeService employeeService = new EmployeeService();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Employee> employees = new EmployeeService().getAll();
        int id = Integer.parseInt(req.getParameter("id"));
//        HttpSession session = req.getSession(true);
//        session.setMaxInactiveInterval(50);
        req.setAttribute("employee",  new EmployeeService().get(id));
//        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//        resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//        resp.setDateHeader("Expires", 0); // Proxies.
        req.getRequestDispatcher("/WEB-INF/jsp/employeePage.jsp").forward(req,resp);
//        StringBuilder sb = new StringBuilder("<html><head><title>Employees</title></head><body><table>");
//        for (Employee emmployee:employees
//             ) {
//            System.out.println(emmployee.getName());
//            sb.append("<tr><td>");
//            sb.append(emmployee.getId());
//            sb.append("</td><td>");
//            sb.append(emmployee.getName());
//            sb.append("</td><td>");
//            sb.append(emmployee.getSurname());
//            sb.append("</td></tr>");
//        }
//        sb.append("</table>"+ session.getId() +"</body></html>");
//        resp.getOutputStream().write(sb.toString().getBytes());
    }
}
