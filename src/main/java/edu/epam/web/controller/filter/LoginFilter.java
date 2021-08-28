package edu.epam.web.controller.filter;

import edu.epam.web.command.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUtils;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter implements Filter {

    private static final String SLASH_SYMBOL = "/";

    private static final String DEFAULT_LANGUAGE = "en";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String url = request.getRequestURI();
        String action = request.getParameter(RequestParameter.ACTION);
        String loginURI = request.getContextPath() + CommandAction.ACTION_LOGIN;
        String registration = request.getContextPath() + CommandAction.ACTION_REGISTRATION;
        String startPage = request.getContextPath() + SLASH_SYMBOL;
        String homePage = request.getContextPath() + CommandAction.ACTION_HOME;
        String menuPage = request.getContextPath() + CommandAction.ACTION_SHOW_MENU;
        String registrationResult = request.getContextPath() + CommandAction.ACTION_REGISTRATION_RESULT;
        String loginResult = request.getContextPath() + CommandAction.ACTION_LOGIN_RESULT;
        String changeLanguage = request.getContextPath() + CommandAction.ACTION_CHANGE_LANGUAGE;
        String forgotPassword = request.getContextPath() + CommandAction.ACTION_FORGOT_PASSWORD;
        String forgotPasswordResult = request.getContextPath() + CommandAction.ACTION_FORGOT_PASSWORD_RESULT;

        boolean loggedIn = session != null && session.getAttribute(SessionAttribute.USER) != null;
        boolean loginRequest = (request.getRequestURI() + CommandAction.ACTION_URL_PART + action).equals(loginURI);
        boolean registrationRequest = (request.getRequestURI() + CommandAction.ACTION_URL_PART + action).equals(registration);
        boolean homePageRequest = (request.getRequestURI() + CommandAction.ACTION_URL_PART + action).equals(homePage);
        boolean menuPageRequest = (request.getRequestURI() + CommandAction.ACTION_URL_PART + action).equals(menuPage);
        boolean registrationResultrRequest = (request.getRequestURI() + CommandAction.ACTION_URL_PART + action).equals(registrationResult);
        boolean loginResultRequest = (request.getRequestURI() + CommandAction.ACTION_URL_PART + action).equals(loginResult);
        boolean changeLanguageRequest = (request.getRequestURI() + CommandAction.ACTION_URL_PART + action).equals(changeLanguage);
        boolean forgotPasswordRequest = (request.getRequestURI() + CommandAction.ACTION_URL_PART + action).equals(forgotPassword);
        boolean forgotPasswordResultRequest = (request.getRequestURI() + CommandAction.ACTION_URL_PART + action).equals(forgotPasswordResult);

        if (session == null) {
            request.getSession().setAttribute(SessionAttribute.LANGUAGE, DEFAULT_LANGUAGE);
        }

        if (loggedIn || loginRequest || registrationRequest || homePageRequest || menuPageRequest || loginResultRequest ||
                registrationResultrRequest || changeLanguageRequest || forgotPasswordRequest||
                forgotPasswordResultRequest|| url.equals(startPage))  {
            // pass the request along the filter chain
            filterChain.doFilter(request, response);
        } else {
            System.out.println(request.getRequestURI()+CommandAction.ACTION_URL_PART+action);
            System.out.println(forgotPassword);
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}
