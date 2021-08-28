package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserGender;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.factory.UserFactory;
import edu.epam.web.service.DateFormatService;
import edu.epam.web.service.UserDaoService;
import edu.epam.web.utility.EncryptPasswordUtil;
import edu.epam.web.utility.MailSenderUtil;
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

public class RegistrationResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(edu.epam.web.command.impl.RegistrationResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        UserFactory factory = new UserFactory();
        UserDaoService daoService = new UserDaoService();
        DateFormatService formatService = new DateFormatService();
        RequestDispatcher reg = request.getRequestDispatcher(PagePath.REGISTRATION_PAGE);
        Map<String, String> messages = new HashMap<String, String>();
        String telephoneNumber = request.getParameter(RequestParameter.TELEPHONE);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = EncryptPasswordUtil.encrypt(request.getParameter(RequestParameter.PASSWORD));
        String verifyPassword = EncryptPasswordUtil.encrypt(request.getParameter(RequestParameter.VERIFY_PASSWORD));
        String name=request.getParameter(RequestParameter.NAME);
        String surname=request.getParameter(RequestParameter.SURNAME);
        Date birthday=formatService.formatStringToDate(request.getParameter(RequestParameter.BIRTHDAY));
        UserGender gender=UserGender.valueOf(request.getParameter(RequestParameter.GENDER));
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
                User user = factory.createNewUser(0, telephoneNumber,surname, name, birthday, gender, email, null);
                int flag = daoService.createUser(user, password);
                if (flag == 0) {
                    messages.put("message", "Registration failed, please try again");
                    request.setAttribute(RequestAttribute.USER, user);
                } else {
                    messages.put("message", "Registration successful, activate account by email, please");
                    MailSenderUtil.sendMessage(user.getEmail(), user.getName(), user.getSurname());
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }

        request.setAttribute(RequestAttribute.MESSAGES, messages);
        reg.forward(request, response);
    }
}


