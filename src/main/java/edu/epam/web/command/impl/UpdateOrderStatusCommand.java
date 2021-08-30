package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.entity.*;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.OrderDaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrderStatusCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UpdateOrderStatusCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        OrderDaoService service = new OrderDaoService();
        List<Order> allOrders = new ArrayList<>();
        RequestDispatcher usersPage = request.getRequestDispatcher(PagePath.SHOW_ALL_ORDERS_PAGE);
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        OrderStatus status = OrderStatus.valueOf(request.getParameter(RequestParameter.STATUS));
        try {
            service.updateOrderStatus(id, status);
            allOrders = service.findOrders();
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
            error.forward(request,response);
        }
        request.setAttribute(RequestAttribute.ALL_ORDERS, allOrders);
        usersPage.forward(request, response);
    }
}
