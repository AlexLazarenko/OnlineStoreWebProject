package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.Dish;
import edu.epam.web.model.entity.Order;
import edu.epam.web.model.entity.OrderStatus;
import edu.epam.web.model.entity.User;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.OrderService;
import edu.epam.web.model.service.impl.OrderServiceImpl;
import edu.epam.web.utility.PriceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderCommand extends Command {
    private static final Logger logger = LogManager.getLogger(MakeOrderCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        OrderService service = new OrderServiceImpl();
        Map<String, String> messages = new HashMap<String, String>();
        Map<Dish, Integer> dishMap = (Map<Dish, Integer>) request.getSession().getAttribute(SessionAttribute.MAP);
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        int userId = user.getId();
        BigDecimal discount = user.getUserStatus().getDiscount();
        BigDecimal totalPrice = PriceCountUtil.count(dishMap);
        BigDecimal discountPrice = PriceCountUtil.countDiscountPrice(totalPrice, discount);
        Order order = new Order(0, userId, discountPrice, discount, new Date(), OrderStatus.NEW, dishMap);
        try {
            int flag = service.createOrder(order, userId);
            if (flag == 0) {
                messages.put("message", "Creation order failed, please try again");
            } else {
                messages.put("message", "Order successfully created");
                dishMap.clear();
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        request.getSession().setAttribute(SessionAttribute.MAP, dishMap);

        Router router = new Router(PagePath.MAIN_PAGE);
        return router;
    }
}
