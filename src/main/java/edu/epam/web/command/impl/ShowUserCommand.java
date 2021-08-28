package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowUserCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher showUser = request.getRequestDispatcher(PagePath.SHOW_USER_PAGE);
        showUser.forward(request, response);
    }
}

