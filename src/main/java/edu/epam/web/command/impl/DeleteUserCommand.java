package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.entity.User;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.service.UserDaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteUserCommand extends Command {
    private static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoService service = new UserDaoService();
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        RequestDispatcher usersPage = request.getRequestDispatcher(PagePath.VIEW_ALL_USERS_PAGE);
        List<User> allUsers = new ArrayList<>();
        try {
            service.deleteUser(id);
            allUsers = service.findUsers();
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
            error.forward(request,response);
        }

        request.setAttribute(RequestAttribute.ALL_USERS, allUsers);
        usersPage.forward(request, response);
    }
}

