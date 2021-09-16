package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.Order;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.OrderService;
import edu.epam.web.utility.DateFormatUtil;
import edu.epam.web.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ShowUserOrdersByIdDateCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ShowUserOrdersByIdDateCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        OrderService service = new OrderServiceImpl();
        //  User storedUser = (User) request.getSession().getAttribute("user");
        int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        Date dateFrom;
        Date dateTo;
        try {
            dateFrom = DateFormatUtil.formatStringToDate(request.getParameter(RequestParameter.DATE_FROM));
            dateTo = DateFormatUtil.formatStringToDate(request.getParameter(RequestParameter.DATE_TO));
        } catch (ParseException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        List<Order> allOrders;
        try {
            allOrders = service.findOrders(userId, dateFrom, dateTo);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(RequestAttribute.ALL_ORDERS, allOrders);
        Router router = new Router(PagePath.SHOW_ALL_ORDERS_PAGE);
        return router;
    }
}

