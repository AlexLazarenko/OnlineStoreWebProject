package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.Router;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.Dish;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.DishService;
import edu.epam.web.model.service.impl.DishServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowMenuCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ShowMenuCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        DishService service = new DishServiceImpl();
        List<Dish> dishList;
        try {
            dishList = service.findDishes();
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(RequestAttribute.DISH_LIST, dishList);
        Router router = new Router(PagePath.MENU_PAGE);
        return router;
    }
}
