package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.DishStatus;
import edu.epam.web.entity.Ingredient;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.factory.DishFactory;
import edu.epam.web.service.DishDaoService;
import edu.epam.web.validator.BigDecimalValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

public class AddDishResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(AddDishResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        RequestDispatcher addDish = request.getRequestDispatcher(PagePath.ADD_DISH_PAGE);
        Map<String, String> messages = new HashMap<String, String>();
        DishDaoService service = new DishDaoService();
        DishFactory dishFactory=new DishFactory();
        String dishName = request.getParameter(RequestParameter.DISH_NAME);
        String size = request.getParameter(RequestParameter.SIZE);
        String stringPrice = request.getParameter(RequestParameter.PRICE);
        if(BigDecimalValidator.validate(stringPrice)){
            BigDecimal price = new BigDecimal(stringPrice);//todo validation only numbers + .
            String clientInfo = request.getParameter(RequestParameter.CLIENT_INFO);
            String staffInfo = request.getParameter(RequestParameter.STAFF_INFO);
            String[] names = request.getParameterValues(RequestParameter.NAME);
            String[] quantity = request.getParameterValues(RequestParameter.QUANTITY);
            List<Ingredient> ingredients = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                if (names[i] != null && quantity[i] != null) {
                    Ingredient ingredient = new Ingredient(names[i], quantity[i]);
                    ingredients.add(ingredient);
                }
            }
            Dish dish=dishFactory.createDish(0,dishName, size, price, clientInfo, staffInfo, null, DishStatus.NEW, ingredients);
            try{
                int result=service.createDish(dish);
                if (result == 0) {
                    logger.error("Add dish failed. Try again!");
                    messages.put("message","Add dish failed. Try again!");
                    request.setAttribute(RequestAttribute.DISH,dish);
                } else {
                    logger.info("The dish was added successfully");
                    messages.put("message","the dish was added successfully");
                }
            }catch (ServiceException e){
                logger.error(e);
                request.setAttribute(RequestAttribute.EXCEPTION,e.getMessage());
                RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
                error.forward(request,response);
            }
        }else {
            logger.error("Price is not correct!");
            messages.put("message","Price is not correct!");
        }
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        addDish.forward(request, response);
    }
}
