package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.*;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.DishService;
import edu.epam.web.model.service.impl.DishServiceImpl;
import edu.epam.web.utility.DishOrderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AddDishOrderCommand extends Command {
    private static final Logger logger = LogManager.getLogger(AddDishOrderCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        DishService service = new DishServiceImpl();
        List<Dish> dishList = new ArrayList<>();
        Map<Dish, Integer> dishMap = (Map<Dish, Integer>) request.getSession().getAttribute(SessionAttribute.MAP);
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        try {
            Optional<Dish> optionalDish = service.findDishById(id);
            if (optionalDish.isPresent()) {
                int dishQuantity = Integer.parseInt(request.getParameter(RequestParameter.QUANTITY));
                DishOrderUtil.putDish(dishMap, optionalDish, dishQuantity);
            } else {
                logger.info("Dish with this id is not present");
            }
            request.getSession().setAttribute(SessionAttribute.MAP, dishMap);

            dishList = service.findDishes();
            request.setAttribute(RequestAttribute.DISH_LIST, dishList);
            Router router=new Router(PagePath.MENU_PAGE);
            return router;
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        //todo !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
}
