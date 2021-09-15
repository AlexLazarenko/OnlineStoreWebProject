package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.*;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.OrderService;
import edu.epam.web.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrderStatusCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UpdateOrderStatusCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        OrderService service = new OrderServiceImpl();
        List<Order> allOrders = new ArrayList<>();
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        OrderStatus status = OrderStatus.valueOf(request.getParameter(RequestParameter.STATUS));
        try {
            service.updateOrderStatus(id, status);
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
