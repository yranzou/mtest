package com.mtest.webapp;

import com.mtest.dao.LoginDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by yuri on 30.11.17.
 */
public class LoginServlet extends HttpServlet {
    private final long serialVersionID = 1L;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("uname");
        String password = req.getParameter("psw");
        String remember = req.getParameter("remember");

        boolean isValidated = LoginDao.validate(name, password);
        if (isValidated) {
            if (remember != null && remember.equals("on")) {
                Cookie usernameCookie = new Cookie("name", name);
//                Cookie passwordCookie = new Cookie("password", password);
                resp.addCookie(usernameCookie);
//                resp.addCookie(new Cookie("password", password));
            }
            HttpSession session = req.getSession();
//            session.setMaxInactiveInterval(30);
            session.setAttribute("name", name);
//            session.setAttribute("password", password);


            resp.sendRedirect(resp.encodeRedirectURL("employee/all"));
        } else {
            String failMessage = "Sorry UserName or Password Error!";
            req.setAttribute("failMessage", failMessage);
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.include(req, resp);
//            req.getRequestDispatcher("login").include(req, resp);
        }
//        resp.sendRedirect("/displayEmployees");
//        resp.sendRedirect(resp.encodeRedirectURL("displayEmployees"));
    }
}
