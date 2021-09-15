package edu.epam.web.model.service;

import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.entity.Order;
import edu.epam.web.model.entity.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {


    int createOrder(Order order, int userId) throws ServiceException;


    Optional<Order> findOrderById(int idx) throws ServiceException;


    List<Order> findOrders(Date dateFrom, Date dateTo) throws ServiceException; //for admin!!! from date to date period


    List<Order> findOrders(int userId, Date dateFrom, Date dateTo) throws ServiceException; //user orders from date to date period


    List<Order> findOrders(int userId) throws ServiceException;  //for user!!! user orders 3 month period


    List<Order> findOrders() throws ServiceException; //for admin!!! all orders 3 month period


    int updateOrderStatus(int orderId, OrderStatus orderStatus) throws ServiceException;


    int updateOrderStatusPoints(int orderId, OrderStatus orderStatus) throws ServiceException;

}
