package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.Dish;
import edu.epam.web.model.entity.User;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.DishService;
import edu.epam.web.model.service.impl.DishServiceImpl;
import edu.epam.web.utility.PriceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class UpdateShoppingCartCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UpdateShoppingCartCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        DishService service = new DishServiceImpl();
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
            throw new CommandException(e);
        }

        request.getSession().setAttribute(SessionAttribute.MAP, dishMap);

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
