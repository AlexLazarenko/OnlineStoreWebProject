package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.entity.*;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.DishDaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

public class UpdateDishResultCommand extends Command {//todo
    private static final Logger logger = LogManager.getLogger(UpdateDishResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {

        RequestDispatcher update = request.getRequestDispatcher(PagePath.UPDATE_DISH_PAGE);
        Map<String, String> messages = new HashMap<String, String>();

        DishDaoService service = new DishDaoService();
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        String dishName = request.getParameter(RequestParameter.DISH_NAME);
        String size = request.getParameter(RequestParameter.SIZE);
        String stringPrice = request.getParameter(RequestParameter.PRICE);
        BigDecimal price = new BigDecimal(stringPrice);//todo validation only numbers + .
        String clientInfo = request.getParameter(RequestParameter.CLIENT_INFO);
        String staffInfo = request.getParameter(RequestParameter.STAFF_INFO);
        String[] names = request.getParameterValues(RequestParameter.NAME);
        String[] quantity = request.getParameterValues(RequestParameter.QUANTITY);
        DishStatus status = DishStatus.valueOf(request.getParameter(RequestParameter.DISH_STATUS));
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && quantity[i] != null) {
                Ingredient ingredient = new Ingredient(names[i], quantity[i]);
                ingredients.add(ingredient);
            }
        }
        Dish dish = new Dish(id, dishName, size, price, clientInfo, staffInfo, null, status, ingredients);
        try {
            int flag = service.updateDish(dish);
            if (flag == 0) {
                messages.put("message", "Update dish failed, please try again");
            } else {
                messages.put("message", "Update dish successful");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }

        request.setAttribute(RequestAttribute.MESSAGES, messages);
        update.forward(request, response);
    }
}
