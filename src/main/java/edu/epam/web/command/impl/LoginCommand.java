package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ValidatorException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        RequestDispatcher login = request.getRequestDispatcher(PagePath.LOGIN_PAGE);
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        login.forward(request, response);
    }
}
