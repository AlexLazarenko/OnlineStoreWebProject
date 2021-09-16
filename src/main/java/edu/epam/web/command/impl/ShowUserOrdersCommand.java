package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.Order;
import edu.epam.web.model.entity.User;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.OrderService;
import edu.epam.web.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowUserOrdersCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ShowUserOrdersCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        OrderService service = new OrderServiceImpl();
        User storedUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        int userId = storedUser.getId();
        List<Order> allOrders;
        try {
            allOrders = service.findOrders(userId);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(RequestAttribute.ALL_ORDERS, allOrders);
        Router router = new Router(PagePath.SHOW_ALL_ORDERS_PAGE);
        return router;
    }
}
