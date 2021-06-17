package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.Dish;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.DishDaoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ShowMenuCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        DishDaoService service = new DishDaoService();
        List<Dish> dishList = new ArrayList<>();
        RequestDispatcher usersPage = request.getRequestDispatcher("/jsp/menu.jsp");
        dishList = service.findDishes();
        request.setAttribute("dishList", dishList);
        usersPage.forward(request, response);
    }
}
