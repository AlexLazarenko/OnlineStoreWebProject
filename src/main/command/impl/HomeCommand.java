package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        Router router = new Router(PagePath.MAIN_PAGE);
        return router;
    }
}