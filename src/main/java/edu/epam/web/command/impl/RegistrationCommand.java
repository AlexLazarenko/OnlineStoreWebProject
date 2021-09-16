package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.Router;
import edu.epam.web.model.entity.*;
import edu.epam.web.model.factory.UserFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> messages = new HashMap<String, String>();
        UserFactory factory = new UserFactory();
        User user = new User(0, "", "", "", new Date(), UserGender.NONE, "",
                null,UserRole.CLIENT, "", UserStatus.SILVER, AccountStatus.NEW);
        request.setAttribute(RequestAttribute.USER, user);
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        Router router = new Router(PagePath.REGISTRATION_PAGE);
        return router;
    }
}
