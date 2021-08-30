package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.entity.Order;
import edu.epam.web.entity.User;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.DateFormatService;
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
import java.util.Date;
import java.util.List;

public class ShowUserOrdersByDateCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ShowUserOrdersByDateCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        OrderDaoService service = new OrderDaoService();
        DateFormatService formatService = new DateFormatService();
        User storedUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        int userId = storedUser.getId();
        Date dateFrom = formatService.formatStringToDate(request.getParameter(RequestParameter.DATE_FROM));
        Date dateTo = formatService.formatStringToDate(request.getParameter(RequestParameter.DATE_TO));
        List<Order> allOrders = new ArrayList<>();
        RequestDispatcher usersPage = request.getRequestDispatcher(PagePath.SHOW_ALL_ORDERS_PAGE);
        try{
            allOrders = service.findOrders(userId, dateFrom, dateTo);
        }catch (ServiceException e){
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
            error.forward(request,response);
        }
        request.setAttribute(RequestAttribute.ALL_ORDERS, allOrders);
        usersPage.forward(request, response);
    }
}

