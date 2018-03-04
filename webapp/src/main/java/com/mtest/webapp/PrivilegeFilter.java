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
public class PrivilegeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(30);
        if (!"admin".equals(session.getAttribute("name"))) {
            request.getRequestDispatcher("login").forward(request, response);
        }
//        response.sendRedirect(response.encodeRedirectURL("login"));
        chain.doFilter(request, response);

    }


    @Override
    public void destroy() {

    }
}
