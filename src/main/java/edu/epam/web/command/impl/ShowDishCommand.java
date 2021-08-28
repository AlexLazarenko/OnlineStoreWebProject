package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.entity.Dish;
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
import java.text.ParseException;
import java.util.Optional;

public class ShowDishCommand extends Command {
    private static final Logger logger = LogManager.getLogger(edu.epam.web.command.impl.ShowDishCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        RequestDispatcher showDish = request.getRequestDispatcher(PagePath.SHOW_DISH_PAGE);
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        DishDaoService service = new DishDaoService();
        try {
            Optional<Dish> optionalDish = service.findDishById(id);
            optionalDish.ifPresent(value -> request.setAttribute(RequestAttribute.DISH, value)); //todo set list?
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        showDish.forward(request, response);
    }
}
