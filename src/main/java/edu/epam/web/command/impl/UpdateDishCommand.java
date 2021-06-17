package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.Ingredient;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.DishDaoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateDishCommand extends Command {//todo заставить подставлять список ингридиентов

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        Dish dish = null;
        RequestDispatcher update = request.getRequestDispatcher("/jsp/updateDish.jsp");
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        int id = Integer.parseInt(request.getParameter("id"));
        DishDaoService service = new DishDaoService();
        dish = service.findDishById(id);
         List<Ingredient> ingredientList=dish.getIngredientList();
        request.setAttribute("dish", dish);
        request.setAttribute("list",ingredientList);
        update.forward(request, response);
    }
}
