package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.*;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.factory.DishFactory;
import edu.epam.web.model.service.DishService;
import edu.epam.web.model.service.impl.DishServiceImpl;
import edu.epam.web.validator.BigDecimalValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateDishResultCommand extends Command {//todo
    private static final Logger logger = LogManager.getLogger(UpdateDishResultCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Map<String, String> messages = new HashMap<String, String>();
        DishFactory dishFactory = new DishFactory();
        DishService service = new DishServiceImpl();
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        String dishName = request.getParameter(RequestParameter.DISH_NAME);
        String size = request.getParameter(RequestParameter.SIZE);
        String stringPrice = request.getParameter(RequestParameter.PRICE);
        if (BigDecimalValidator.validate(stringPrice)) {
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
            Dish dish = dishFactory.createDish(id, dishName, size, price, clientInfo, staffInfo, null, status, ingredients);
            try {
                int flag = service.updateDish(dish);
                if (flag == 0) {
                    messages.put("message", "Update dish failed, please try again");
                    request.setAttribute(RequestAttribute.DISH, dish);
                } else {
                    messages.put("message", "Update dish successful");
                }
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        } else {
            logger.error("Price is not correct!");
            messages.put("message", "Price is not correct!");
        }

        request.setAttribute(RequestAttribute.MESSAGES, messages);

        Router router = new Router(PagePath.UPDATE_DISH_PAGE);
        return router;
    }
}
