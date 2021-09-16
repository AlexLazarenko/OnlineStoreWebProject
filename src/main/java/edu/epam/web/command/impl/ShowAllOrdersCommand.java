package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.Router;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.Order;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.OrderService;
import edu.epam.web.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllOrdersCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ShowAllOrdersCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        OrderService service = new OrderServiceImpl();
        List<Order> allOrders;
        try {
            allOrders = service.findOrders();
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(RequestAttribute.ALL_ORDERS, allOrders);
        Router router = new Router(PagePath.SHOW_ALL_ORDERS_PAGE);
        return router;
    }
}

