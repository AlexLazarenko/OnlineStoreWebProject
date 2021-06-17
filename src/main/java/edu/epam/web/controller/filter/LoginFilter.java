package edu.epam.web.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    /*    HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String url = request.getRequestURI();
        String loginURI = request.getContextPath() + "/login";
        String registration = request.getContextPath() + "/reg";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest || url.equals(registration)) { //todo

            // pass the request along the filter chain
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }*/
    }

    @Override
    public void destroy() {

    }
}
