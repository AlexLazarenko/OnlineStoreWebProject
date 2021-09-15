package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.Router;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.User;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.UserService;
import edu.epam.web.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


public class ShowAllUsersCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ShowAllUsersCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService service = new UserServiceImpl();
        List<User> allUsers = new ArrayList<>();
        try {
            allUsers = service.findUsers();
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(RequestAttribute.ALL_USERS, allUsers);
        Router router = new Router(PagePath.VIEW_ALL_USERS_PAGE);
        return router;
    }
}
