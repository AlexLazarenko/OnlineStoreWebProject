package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.User;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.DishDaoService;
import edu.epam.web.service.PriceCountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

public class UpdateShoppingCartCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UpdateShoppingCartCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        DishDaoService service = new DishDaoService();
        RequestDispatcher showUser = request.getRequestDispatcher(PagePath.SHOPPING_CART_PAGE);
        Map<Dish, Integer> dishMap = (Map<Dish, Integer>) request.getSession().getAttribute(SessionAttribute.MAP);

        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        try {
            Optional<Dish> optionalDish = service.findDishById(id);
            if (optionalDish.isPresent()) {
                int dishQuantity = Integer.parseInt(request.getParameter(RequestParameter.QUANTITY));
                if (dishQuantity == 0) {
                    dishMap.remove(optionalDish.get());
                } else {
                    dishMap.put(optionalDish.get(), dishQuantity);
                }
            } else {
                logger.warn("This dish is not present");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
            error.forward(request,response);
        }

        request.getSession().setAttribute(SessionAttribute.MAP, dishMap);

        BigDecimal totalPrice = PriceCountService.count(dishMap);
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        BigDecimal discount = user.getUserStatus().getDiscount();
        BigDecimal discountPrice = PriceCountService.countDiscountPrice(totalPrice, discount);
        request.setAttribute(RequestAttribute.DISCOUNT, discount);
        request.setAttribute(RequestAttribute.TOTAL_PRICE, totalPrice);
        request.setAttribute(RequestAttribute.DISCOUNT_PRICE, discountPrice);
        showUser.forward(request, response);
    }
}
