package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.SessionAttribute;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.Order;
import edu.epam.web.entity.OrderStatus;
import edu.epam.web.entity.User;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.OrderDaoService;
import edu.epam.web.service.PriceCountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderCommand extends Command {
    private static final Logger logger = LogManager.getLogger(MakeOrderCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        OrderDaoService service=new OrderDaoService();
        Map<String, String> messages = new HashMap<String, String>();
        RequestDispatcher makeOrder = request.getRequestDispatcher(PagePath.MAIN_PAGE);
        Map<Dish,Integer> dishMap= (Map<Dish, Integer>) request.getSession().getAttribute(SessionAttribute.MAP);
        User user= (User) request.getSession().getAttribute(SessionAttribute.USER);
        int userId=user.getId();
        BigDecimal discount=user.getUserStatus().getDiscount();
        BigDecimal totalPrice= PriceCountService.count(dishMap);
        BigDecimal discountPrice=PriceCountService.countDiscountPrice(totalPrice,discount);
        Order order=new Order(0,userId,discountPrice,discount,new  Date(), OrderStatus.NEW,dishMap);
        try {
            int flag=service.createOrder(order,userId);
            if (flag == 0) {
                messages.put("message", "Creation order failed, please try again");
            } else {
                messages.put("message", "Order successfully created");
                dishMap.clear();
            }
        }catch (ServiceException e){
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        request.getSession().setAttribute(SessionAttribute.MAP, dishMap);
        makeOrder.forward(request, response);
    }
}
