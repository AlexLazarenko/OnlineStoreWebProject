package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ValidatorException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

public class ChangeLanguageCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        HttpSession session = request.getSession();
        String language = request.getParameter(RequestParameter.LANGUAGE);
        session.setAttribute(SessionAttribute.LANGUAGE, language);
        RequestDispatcher comeback = request.getRequestDispatcher(PagePath.MAIN_PAGE);
        comeback.forward(request, response);
    }
}
