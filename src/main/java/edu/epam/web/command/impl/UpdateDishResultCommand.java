package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserGender;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.factory.UserFactory;
import edu.epam.web.service.DateFormatService;
import edu.epam.web.service.DishDaoService;
import edu.epam.web.service.UserDaoService;
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

public class UpdateDishResultCommand extends Command {//todo
    private static final Logger logger = LogManager.getLogger(UpdateDishResultCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        DishDaoService daoService = new DishDaoService();
        RequestDispatcher update = request.getRequestDispatcher("/jsp/updateDish.jsp");
       Map<String, String> messages = new HashMap<String, String>();
/*
        User storedUser = (User) request.getSession().getAttribute("user");
        String telephoneNumber = request.getParameter("telephone");
        String email = request.getParameter("email");
        String storedTelephoneNumber=storedUser.getTelephoneNumber();
        String storedEmail=storedUser.getEmail();

        if (!email.equals(storedEmail)){
            if (daoService.findUserEmail(email) != null) {
                logger.warn("This email already exists");
                messages.put("email", "This email already exists");
            }
        }
        if (!telephoneNumber.equals(storedTelephoneNumber)){
            if (daoService.findUserTelephoneNumber(telephoneNumber) != null) {
                logger.warn("This telephone number already exists");
                messages.put("telephone", "This telephone number already exists");
            }
        }
        if(messages.isEmpty()) {
            User user = factory.createUser(storedUser.getId(), telephoneNumber,
                    request.getParameter("surname"), request.getParameter("name"),
                    formatService.formatStringToDate(request.getParameter("birthday")),
                    UserGender.valueOf(request.getParameter("gender")), email,
                    storedUser.getAvatar(), storedUser.getStatusPoint(), storedUser.getRole(), storedUser.getUserStatus(),
                    storedUser.getAccountStatus());
            int flag = daoService.updateUser(user);
            if (flag == 0) {
                messages.put("message", "Update failed, please try again");
                //     request.setAttribute("user", user);
            } else messages.put("message", "Update successful");
        }*/
        request.setAttribute("messages", messages);
        update.forward(request, response);
    }
}
