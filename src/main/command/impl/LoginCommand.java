package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        Router router = new Router(PagePath.LOGIN_PAGE);
        return router;
    }
}
