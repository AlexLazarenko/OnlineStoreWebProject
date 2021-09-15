package edu.epam.web.command.impl;

import edu.epam.web.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = request.getParameter(RequestParameter.LANGUAGE);
        session.setAttribute(SessionAttribute.LANGUAGE, language);
        Router router = new Router(PagePath.MAIN_PAGE);
        return router;
    }
}
