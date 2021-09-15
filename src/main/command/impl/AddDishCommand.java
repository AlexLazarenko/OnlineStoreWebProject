package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.Router;
import edu.epam.web.model.entity.Dish;
import edu.epam.web.model.entity.DishStatus;
import edu.epam.web.model.entity.Ingredient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDishCommand extends Command {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> messages = new HashMap<String, String>();
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("", "");
        ingredients.add(ingredient);
        Dish dish = new Dish(0, "", "", BigDecimal.valueOf(0), "", "", null, DishStatus.NEW, ingredients);
        request.setAttribute(RequestAttribute.DISH, dish);
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        Router router = new Router(PagePath.ADD_DISH_PAGE);
        return router;
    }
}
