package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.entity.AccountStatus;
import edu.epam.web.entity.User;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UpdateAccountStatusCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UpdateAccountStatusCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        UserDaoService service = new UserDaoService();
        List<User> allUsers = new ArrayList<>();
        RequestDispatcher usersPage = request.getRequestDispatcher(PagePath.VIEW_ALL_USERS_PAGE);
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        AccountStatus status = AccountStatus.valueOf(request.getParameter(RequestParameter.ACCOUNT_STATUS));
        try {
            service.updateAccountStatus(id, status);
            allUsers = service.findUsers();
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        request.setAttribute(RequestAttribute.ALL_USERS, allUsers);
        usersPage.forward(request, response);
    }
}
