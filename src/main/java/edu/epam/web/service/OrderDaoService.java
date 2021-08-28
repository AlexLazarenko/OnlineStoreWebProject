package edu.epam.web.service;

import edu.epam.web.dao.impl.OrderDaoImpl;
import edu.epam.web.entity.Order;
import edu.epam.web.entity.OrderStatus;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserStatus;
import edu.epam.web.exception.DaoException;
import edu.epam.web.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderDaoService {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    UserDaoService userDaoService = new UserDaoService();
    private static final Logger logger = LogManager.getLogger(OrderDaoService.class);


    public int createOrder(Order order, int userId) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = orderDao.createOrder(order, userId);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<Order> findOrderById(int idx) throws ServiceException {
        try {
            Optional<Order> order = orderDao.findOrderById(idx);
            return order;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public List<Order> findOrders(Date dateFrom, Date dateTo) throws ServiceException { //for admin!!! from date to date period
        List<Order> orders;
        try {
            orders = orderDao.findOrders(dateFrom, dateTo);
            return orders;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public List<Order> findOrders(int userId, Date dateFrom, Date dateTo) throws ServiceException { //user orders from date to date period
        List<Order> orders;
        try {
            orders = orderDao.findOrders(userId, dateFrom, dateTo);
            return orders;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public List<Order> findOrders(int userId) throws ServiceException {  //for user!!! user orders 3 month period
        List<Order> orders;
        try {
            orders = orderDao.findOrders(userId, new Date(System.currentTimeMillis() - 2628000000L * 3), new Date());
            return orders;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public List<Order> findOrders() throws ServiceException {  //for admin!!! all orders 3 month period
        List<Order> orders;
        try {
            orders = orderDao.findOrders(new Date(System.currentTimeMillis() - 2628000000L * 3), new Date());
            return orders;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateOrderStatus(int orderId, OrderStatus orderStatus) throws ServiceException {
        int affectedRows = 0;
        try {
            if (orderStatus.name().equals("DONE")) {
                affectedRows = updateOrderStatusPoints(orderId, orderStatus);
            } else {
                affectedRows = orderDao.updateOrderStatus(orderId, orderStatus);
            }
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateOrderStatusPoints(int orderId, OrderStatus orderStatus) throws ServiceException {
        int affectedRows = 0;
        try {
            Optional<Order> optionalOrder = findOrderById(orderId);
            if (optionalOrder.isPresent()) {
                BigDecimal price = optionalOrder.get().getPrice();
                int userId = optionalOrder.get().getUserId();
                Optional<User> optionalStoredUser = userDaoService.findUserById(userId);
                if (optionalStoredUser.isPresent()) {
                    BigDecimal statusPoint = optionalStoredUser.get().getStatusPoint().add(price);
                    UserStatus newStatus = UserStatusPointService.identifyStatus(statusPoint);
                    affectedRows = orderDao.updateOrderStatusPoint(orderId, orderStatus, userId, statusPoint, newStatus);
                } else {
                    logger.info("User with this id is not present");
                }
            } else {
                logger.info("Order with this id is not present");
            }
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
