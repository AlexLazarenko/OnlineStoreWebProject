package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.entity.*;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.DishDaoService;
import edu.epam.web.utility.DishOrderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class AddDishOrderCommand extends Command {
    private static final Logger logger = LogManager.getLogger(AddDishOrderCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        DishDaoService service = new DishDaoService();
        List<Dish> dishList = new ArrayList<>();
        RequestDispatcher menu = request.getRequestDispatcher(PagePath.MENU_PAGE);
        Map<Dish, Integer> dishMap = (Map<Dish, Integer>) request.getSession().getAttribute(SessionAttribute.MAP);
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        try{
            Optional<Dish> optionalDish = service.findDishById(id);
            if (optionalDish.isPresent()){
            int dishQuantity = Integer.parseInt(request.getParameter(RequestParameter.QUANTITY));
                DishOrderUtil.putDish(dishMap,optionalDish,dishQuantity);
            }else {
                logger.info("Dish with this id is not present");
            }
            request.getSession().setAttribute(SessionAttribute.MAP, dishMap);

            dishList = service.findDishes();
            request.setAttribute(RequestAttribute.DISH_LIST, dishList);
            menu.forward(request, response);
        }catch (ServiceException e){
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION,e.getMessage());
            RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
            error.forward(request,response);
        }

        //todo

    }
}
