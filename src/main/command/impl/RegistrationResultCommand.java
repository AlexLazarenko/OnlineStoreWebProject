package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.User;
import edu.epam.web.model.entity.UserGender;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.factory.UserFactory;
import edu.epam.web.model.service.UserService;
import edu.epam.web.utility.DateFormatUtil;
import edu.epam.web.model.service.impl.UserServiceImpl;
import edu.epam.web.utility.EncryptPasswordUtil;
import edu.epam.web.utility.MailSenderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistrationResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(RegistrationResultCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserFactory factory = new UserFactory();
        UserService daoService = new UserServiceImpl();
        Map<String, String> messages = new HashMap<String, String>();
        String telephoneNumber = request.getParameter(RequestParameter.TELEPHONE);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = EncryptPasswordUtil.encrypt(request.getParameter(RequestParameter.PASSWORD));
        String verifyPassword = EncryptPasswordUtil.encrypt(request.getParameter(RequestParameter.VERIFY_PASSWORD));
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        Date birthday = null;
        try {
            birthday = DateFormatUtil.formatStringToDate(request.getParameter(RequestParameter.BIRTHDAY));
        } catch (ParseException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        UserGender gender = UserGender.valueOf(request.getParameter(RequestParameter.GENDER));
        try {
            if (daoService.findUserTelephoneNumber(telephoneNumber) != null) {
                logger.warn("This telephone number already exists");
                messages.put("telephone", "This telephone number already exists");
            }
            if (daoService.findUserEmail(email) != null) {
                logger.warn("This email already exists");
                messages.put("email", "This email already exists");
            }
            if (!EncryptPasswordUtil.checkPassword(password, verifyPassword)) {
                logger.warn("Checking password fail");
                messages.put("message", "Registration failed, checking password fail");
            }
            if (messages.isEmpty()) {
                User user = factory.createNewUser(0, telephoneNumber, surname, name, birthday, gender, email, null);
                int flag = daoService.createUser(user, password);
                if (flag == 0) {
                    messages.put("message", "Registration failed, please try again");
                    request.setAttribute(RequestAttribute.USER, user);
                } else {
                    messages.put("message", "Registration successful, activate account by email, please");
                    MailSenderUtil.sendMessage(user.getEmail(), user.getName(), user.getSurname());
                }
            } else {
                User user = factory.createNewUser(0, telephoneNumber, surname, name, birthday, gender, email, null);
                request.setAttribute(RequestAttribute.USER, user);
            }
        } catch (ServiceException | EmailException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        request.setAttribute(RequestAttribute.MESSAGES, messages);
        Router router = new Router(PagePath.REGISTRATION_PAGE);
        return router;
    }
}


