package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher update = request.getRequestDispatcher(PagePath.UPDATE_USER_PAGE);
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        update.forward(request, response);
    }
}
