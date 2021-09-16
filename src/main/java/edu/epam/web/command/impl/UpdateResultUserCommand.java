package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.User;
import edu.epam.web.model.entity.UserGender;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.factory.UserFactory;
import edu.epam.web.model.service.UserService;
import edu.epam.web.utility.DateFormatUtil;
import edu.epam.web.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UpdateResultUserCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UpdateResultUserCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserFactory factory = new UserFactory();
        UserService daoService = new UserServiceImpl();
        Map<String, String> messages = new HashMap<String, String>();

        User storedUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        String telephoneNumber = request.getParameter(RequestParameter.TELEPHONE);
        String email = request.getParameter(RequestParameter.EMAIL);
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        Date birthday;
        try {
            birthday = DateFormatUtil.formatStringToDate(request.getParameter(RequestParameter.BIRTHDAY));
        } catch (ParseException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        UserGender gender = UserGender.valueOf(request.getParameter(RequestParameter.GENDER));
        String storedTelephoneNumber = storedUser.getTelephoneNumber();
        String storedEmail = storedUser.getEmail();
        try {
            if (!email.equals(storedEmail)) {
                if (daoService.findUserEmail(email) != null) {
                    logger.warn("New email already exists");
                    messages.put("email", "New email already exists");
                }
            }
            if (!telephoneNumber.equals(storedTelephoneNumber)) {
                if (daoService.findUserTelephoneNumber(telephoneNumber) != null) {
                    logger.warn("New telephone number already exists");
                    messages.put("telephone", "New telephone number already exists");
                }
            }
            if (messages.isEmpty()) {
                Optional<User> user = factory.createUser(storedUser.getId(), telephoneNumber, surname, name, birthday, gender, email,
                        storedUser.getAvatar(), storedUser.getStatusPoint(), storedUser.getRole(), storedUser.getUserStatus(),
                        storedUser.getAccountStatus());
                if (user.isPresent()) {
                    int flag = daoService.updateUser(user.get());
                    if (flag == 0) {
                        messages.put("message", "Update failed, please try again");
                        request.setAttribute(RequestAttribute.USER, user);
                    } else {
                        messages.put("message", "Update successful");
                    }
                } else {
                    messages.put("message", "Validation failed, input correct data, please");
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(RequestAttribute.MESSAGES, messages);

        Router router = new Router(PagePath.UPDATE_USER_PAGE);
        return router;
    }
}

