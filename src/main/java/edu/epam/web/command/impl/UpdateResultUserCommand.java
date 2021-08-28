package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserGender;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.factory.UserFactory;
import edu.epam.web.service.DateFormatService;
import edu.epam.web.service.UserDaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateResultUserCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UpdateResultUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        UserFactory factory = new UserFactory();
        UserDaoService daoService = new UserDaoService();
        DateFormatService formatService = new DateFormatService();
        RequestDispatcher update = request.getRequestDispatcher(PagePath.UPDATE_USER_PAGE);
        Map<String, String> messages = new HashMap<String, String>();

        User storedUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        String telephoneNumber = request.getParameter(RequestParameter.TELEPHONE);
        String email = request.getParameter(RequestParameter.EMAIL);
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        Date birthday = formatService.formatStringToDate(request.getParameter(RequestParameter.BIRTHDAY));
        UserGender gender = UserGender.valueOf(request.getParameter(RequestParameter.GENDER));
        String storedTelephoneNumber = storedUser.getTelephoneNumber();
        String storedEmail = storedUser.getEmail();
        try {
            if (!email.equals(storedEmail)) {
                if (daoService.findUserEmail(email) != null) {
                    logger.warn("This email already exists");
                    messages.put("email", "This email already exists");
                }
            }
            if (!telephoneNumber.equals(storedTelephoneNumber)) {
                if (daoService.findUserTelephoneNumber(telephoneNumber) != null) {
                    logger.warn("This telephone number already exists");
                    messages.put("telephone", "This telephone number already exists");
                }
            }
            if (messages.isEmpty()) {
                User user = factory.createUser(storedUser.getId(), telephoneNumber, surname, name, birthday, gender, email,
                        storedUser.getAvatar(), storedUser.getStatusPoint(), storedUser.getRole(), storedUser.getUserStatus(),
                        storedUser.getAccountStatus());
                int flag = daoService.updateUser(user);
                if (flag == 0) {
                    messages.put("message", "Update failed, please try again");
                    request.setAttribute(RequestAttribute.USER, user);
                } else messages.put("message", "Update successful");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        update.forward(request, response);
    }
}

