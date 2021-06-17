package edu.epam.web.controller.filter;

import edu.epam.web.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
     /*   HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String homeURI = request.getContextPath() + "/Home";

        User user = (User) session.getAttribute("user");
        boolean loggedIn =  session.getAttribute("user") != null && user != null
                && request.getParameter("action") != null;
        if (loggedIn  && user.getRole().name().equals("CLIENT") //todo
                && (request.getParameter("action").equals("delete")
                || request.getParameter("action").equals("edit")
                || request.getParameter("action").equals("editResult")
                || request.getParameter("action").equals("showUsers")
                || request.getParameter("action").equals("upUser")
                || request.getParameter("action").equals("deleteUser"))) {
            response.sendRedirect(homeURI);
        } else {

            // pass the request along the filter chain
            filterChain.doFilter(request, response);
        }*/
    }

    @Override
    public void destroy() {

    }
}
