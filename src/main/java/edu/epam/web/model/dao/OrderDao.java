package edu.epam.web.model.dao;

import edu.epam.web.model.entity.Order;
import edu.epam.web.model.entity.OrderStatus;
import edu.epam.web.model.entity.UserStatus;
import edu.epam.web.exception.DaoException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends BaseDao{

    int createOrder(Order order, int userId) throws DaoException;

    Optional<Order> findOrderById(int idx) throws DaoException;

    List<Order> findOrders(Date dateFrom, Date dateTo) throws DaoException;

    List<Order> findOrders(int userId, Date dateFrom, Date dateTo) throws DaoException;

    int updateOrderStatus(int orderId, OrderStatus orderStatus) throws DaoException;

    int updateOrderStatusPoint(int orderId, OrderStatus orderStatus,int userId, BigDecimal statusPoint, UserStatus newStatus) throws DaoException;


}
