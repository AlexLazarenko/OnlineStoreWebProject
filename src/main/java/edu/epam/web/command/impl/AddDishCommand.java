package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.DishStatus;
import edu.epam.web.entity.Ingredient;
import edu.epam.web.exception.ValidatorException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDishCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        RequestDispatcher addDish = request.getRequestDispatcher(PagePath.ADD_DISH_PAGE);
        Map<String, String> messages = new HashMap<String, String>();
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient=new Ingredient("","");
        ingredients.add(ingredient);
        Dish dish = new Dish(0,"", "", BigDecimal.valueOf(0), "", "", null, DishStatus.NEW, ingredients);
        request.setAttribute(RequestAttribute.DISH,dish);
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        addDish.forward(request, response);
    }
}
