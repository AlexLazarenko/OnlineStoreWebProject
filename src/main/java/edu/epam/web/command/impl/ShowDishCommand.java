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
import java.util.Optional;

public class ShowDishCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ShowDishCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        DishService service = new DishServiceImpl();
        try {
            Optional<Dish> optionalDish = service.findDishById(id);
            optionalDish.ifPresent(value -> request.setAttribute(RequestAttribute.DISH, value)); //todo set list?
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        Router router = new Router(PagePath.SHOW_DISH_PAGE);
        return router;
    }
}
