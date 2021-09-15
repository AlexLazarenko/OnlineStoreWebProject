package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.Dish;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.DishService;
import edu.epam.web.model.service.impl.DishServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UpdateDishCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UpdateDishCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        DishService service = new DishServiceImpl();
        try {
            Optional<Dish> optionalDish = service.findDishById(id);
            optionalDish.ifPresent(dish -> request.setAttribute(RequestAttribute.DISH, dish));
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        Router router = new Router(PagePath.UPDATE_DISH_PAGE);
        return router;
    }
}
