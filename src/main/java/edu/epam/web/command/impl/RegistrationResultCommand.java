package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserGender;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.factory.UserFactory;
import edu.epam.web.service.DateFormatService;
import edu.epam.web.service.UserDaoService;
import edu.epam.web.utility.EncryptPasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(edu.epam.web.command.impl.RegistrationResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        UserFactory factory = new UserFactory();
        UserDaoService daoService = new UserDaoService();
        DateFormatService formatService = new DateFormatService();
        RequestDispatcher reg = request.getRequestDispatcher("/jsp/reg.jsp");
        Map<String, String> messages = new HashMap<String, String>();
        String telephoneNumber = request.getParameter("telephone");
        String email = request.getParameter("email");
        if (telephoneNumber != null && email != null) {
            if (daoService.findUserTelephoneNumber(telephoneNumber) != null) {
                logger.warn("This telephone number already exists");
                messages.put("telephone", "This telephone number already exists");
            }
            if (daoService.findUserEmail(email) != null) {
                logger.warn("This email already exists");
                messages.put("email", "This email already exists");
            }
        }
        String password = EncryptPasswordUtil.encrypt(request.getParameter("password"));
        String verifyPassword = EncryptPasswordUtil.encrypt(request.getParameter("password_two"));
        if (EncryptPasswordUtil.checkPassword(password, verifyPassword)) {
            User user = factory.createUser(0, request.getParameter("telephone"), password,
                    request.getParameter("surname"), request.getParameter("name"), formatService.formatStringToDate(request.getParameter("birthday")),
                    UserGender.valueOf(request.getParameter("gender")), request.getParameter("email"), null);
            int flag = daoService.createUser(user);
            if (flag == 0) {
                messages.put("message", "Registration failed, please try again");
           //     request.setAttribute("user", user);
            } else messages.put("message", "Registration successful, activate account by email, please");
        } else {
            logger.warn("Checking password fail");
            messages.put("message", "Registration failed, checking password fail");
        }
        request.setAttribute("messages", messages);
        reg.forward(request, response);
    }
}


