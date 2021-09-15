package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.CommandAction;
import edu.epam.web.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        Router router = new Router(request.getContextPath() + CommandAction.ACTION_LOGIN, Router.Type.REDIRECT);
        return router;
    }
}
