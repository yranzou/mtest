package com.mtest.webapp;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yuri on 03.03.18.
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30);
//        session.setAttribute("name", "admin");
//        String loginURI = request.getContextPath() + "/login";

//        boolean loggedIn = session != null && session.getAttribute("user") != null && session.getAttribute("password") != null;
//        boolean loginRequest = request.getRequestURI().equals(loginURI);


        //  Not landing a request ， To determine whether there is cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            String userName = null;
            String password = null;
            //  judge cookie Is the username and password consistent with the database ， If the agreement is released ， Otherwise forwarding request to landing page
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())) {
                    userName = cookie.getValue();
                    session.setAttribute("name", userName);
                }
//                if ("password".equals(cookie.getName())) {
//                    password = cookie.getValue();
//                }
            }
//            if (userName != null && password != null && userName.equals("admin") ) {
//                session.setAttribute("name",userName);
//                session.setMaxInactiveInterval(30);
//                chain.doFilter(request, response);
////                return;
//            }
//            if ( loggedIn && session.getAttribute("user").equals("admin") && session.getAttribute("password").equals("1234"))
//            {
//                chain.doFilter(request, response);
////                return;
//            }
//            else {
//                //  Redirected to the login screen
//                req.getRequestDispatcher("login").forward(request, response);
//                return;
//            }
            chain.doFilter(request, response);
        }


        else {
//            req.getRequestDispatcher("login").forward(request, response);
            chain.doFilter(request, response);
//            return;
//            return;
        }
    }





//
//        if (loggedIn || loginRequest) {
//            chain.doFilter(request, response);
//        } else {
//            response.sendRedirect(loginURI);
//        }
//    }

    @Override
    public void destroy() {

    }
}
