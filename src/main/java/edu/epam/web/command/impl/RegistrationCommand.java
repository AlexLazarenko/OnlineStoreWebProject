package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.dao.UserDao;
import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserGender;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.factory.UserFactory;
import edu.epam.web.service.DateFormatService;
import edu.epam.web.service.UserDaoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class RegistrationCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        UserFactory factory = new UserFactory();
        UserDaoService service = new UserDaoService();
        DateFormatService formatService = new DateFormatService();
        RequestDispatcher reg = request.getRequestDispatcher("/jsp/reg.jsp");
        reg.forward(request, response);
        User user = factory.createUser(Integer.parseInt(request.getParameter("id")), request.getParameter("telephone_number"), request.getParameter("password"),
                request.getParameter("surname"), request.getParameter("name"), formatService.formatStringToDate(request.getParameter("birthday")),
                UserGender.valueOf(request.getParameter("user_gender")), request.getParameter("email"), request.getParameter("avatar"));
        service.createUser(user);
    }
}
