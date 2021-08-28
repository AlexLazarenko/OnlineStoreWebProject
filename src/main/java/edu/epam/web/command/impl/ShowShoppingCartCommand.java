package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.SessionAttribute;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.User;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.PriceCountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Map;

public class ShowShoppingCartCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        RequestDispatcher showCart = request.getRequestDispatcher(PagePath.SHOPPING_CART_PAGE);
        Map<Dish,Integer> dishMap= (Map<Dish, Integer>) request.getSession().getAttribute(SessionAttribute.MAP);
        BigDecimal totalPrice=PriceCountService.count(dishMap);
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        BigDecimal discount=user.getUserStatus().getDiscount();
        BigDecimal discountPrice=PriceCountService.countDiscountPrice(totalPrice,discount);
        request.setAttribute(RequestAttribute.DISCOUNT, discount);
        request.setAttribute(RequestAttribute.TOTAL_PRICE, totalPrice);
        request.setAttribute(RequestAttribute.DISCOUNT_PRICE, discountPrice);
        showCart.forward(request, response);
    }
}
