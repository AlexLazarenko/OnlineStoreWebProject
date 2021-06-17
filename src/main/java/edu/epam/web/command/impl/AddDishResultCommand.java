package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.DishStatus;
import edu.epam.web.entity.Ingredient;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.DishDaoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

public class AddDishResultCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        RequestDispatcher addDish = request.getRequestDispatcher("/jsp/addDish.jsp");
        Map<String, String> messages = new HashMap<String, String>();
        DishDaoService service = new DishDaoService();
        String dishName = request.getParameter("dishName");
        String size = request.getParameter("size");
        String stringPrice = request.getParameter("price");
        BigDecimal price = new BigDecimal(stringPrice);//todo validation only numbers + .
        String clientInfo = request.getParameter("clientInfo");
        String staffInfo = request.getParameter("staffInfo");
        String[] names = request.getParameterValues("name");
        String[] quantity = request.getParameterValues("quantity");
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && quantity[i] != null) {
                Ingredient ingredient = new Ingredient(names[i], quantity[i]);
                ingredients.add(ingredient);
            }
        }
        Dish dish = new Dish(0,dishName, size, price, clientInfo, staffInfo, null, DishStatus.NEW, ingredients);
        service.createDish(dish);
        request.setAttribute("messages", messages);
        addDish.forward(request, response);
    }
}
