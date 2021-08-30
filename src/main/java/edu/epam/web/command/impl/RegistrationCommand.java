package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.entity.*;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.factory.UserFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        RequestDispatcher reg = request.getRequestDispatcher(PagePath.REGISTRATION_PAGE);
        Map<String, String> messages = new HashMap<String, String>();
        UserFactory factory = new UserFactory();
        User user = factory.createNewUser(0, "", "", "", new Date(), UserGender.NONE, "", null);
        request.setAttribute(RequestAttribute.USER, user);
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        reg.forward(request, response);
    }
}
