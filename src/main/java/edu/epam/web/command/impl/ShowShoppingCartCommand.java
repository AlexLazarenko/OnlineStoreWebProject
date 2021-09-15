package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.model.entity.Dish;
import edu.epam.web.model.entity.User;
import edu.epam.web.utility.PriceCountUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

public class ShowShoppingCartCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Dish, Integer> dishMap = (Map<Dish, Integer>) request.getSession().getAttribute(SessionAttribute.MAP);
        BigDecimal totalPrice = PriceCountUtil.count(dishMap);
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        BigDecimal discount = user.getUserStatus().getDiscount();
        BigDecimal discountPrice = PriceCountUtil.countDiscountPrice(totalPrice, discount);
        request.setAttribute(RequestAttribute.DISCOUNT, discount);
        request.setAttribute(RequestAttribute.TOTAL_PRICE, totalPrice);
        request.setAttribute(RequestAttribute.DISCOUNT_PRICE, discountPrice);
        Router router = new Router(PagePath.SHOPPING_CART_PAGE);
        return router;
    }
}
