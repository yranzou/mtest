package com.mtest.webapp;

import com.mtest.model.Employee;
import com.mtest.server.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *  Created by yuri on 30.11.17.
 */
public class DisplayEmployeeServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    private EmployeeService employeeService = new EmployeeService();
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Employee> employees = employeeService.getAllEmployees();
//        HttpSession session = req.getSession(true);
//        session.setMaxInactiveInterval(50);
        req.setAttribute("employees", this.employeeService.getAllEmployees());
        req.getRequestDispatcher("/WEB-INF/jsp/employees.jsp").forward(req,resp);
//        StringBuilder sb = new StringBuilder("<html><head><title>Employees</title></head><body><table>");
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
//        sb.append("</table>"+ session.getId() +"</body></html>");
//        resp.getOutputStream().write(sb.toString().getBytes());
    }
}
