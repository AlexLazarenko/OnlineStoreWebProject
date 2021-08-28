package edu.epam.web.dao.impl;

import edu.epam.web.connection.ConnectionPool;
import edu.epam.web.dao.OrderDao;
import edu.epam.web.entity.*;
import edu.epam.web.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static java.lang.String.valueOf;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger(edu.epam.web.dao.impl.OrderDaoImpl.class);

    private final ConnectionPool pool = ConnectionPool.getInstance();

    private final List<Order> orders = new ArrayList<>();

    private static final String CREATE_ORDER = "INSERT INTO orders (userId, date, price, discount, order_status) " +
            "VALUES (?,?,?,?,CAST(? AS order_status));";

    private static final String INSERT_DISH_QUANTITY = "INSERT INTO order_dish (orderid, dish_name, dish_quantity) VALUES (?,?,?);";

    private static final String FIND_ORDER_BY_ID = "SELECT userid, orders.id as ordersid, orders.price as orderprice, date,order_status," +
            "discount,dish_quantity,name,size,dish.price as dishprice,clientinfo,staffinfo,picture,dishstatus,dish.id as dishid,ingridient_name," +
            "ingridient_quantity FROM orders JOIN order_dish on orders.id = order_dish.orderid JOIN dish " +
            "on order_dish.dish_name = dish.name JOIN ingridient on dish.name = ingridient.dish_name where orders.id=?;";

    private static final String FIND_ALL_ORDERS = "SELECT userid, orders.id as ordersid, orders.price as orderprice, date,order_status," +
            "discount,dish_quantity,name,size,dish.price as dishprice,clientinfo,staffinfo,picture,dishstatus,dish.id as dishid,ingridient_name," +
            "ingridient_quantity FROM orders JOIN order_dish on orders.id = order_dish.orderid JOIN dish " +
            "on order_dish.dish_name = dish.name JOIN ingridient on dish.name = ingridient.dish_name where date BETWEEN ? and ?;";

    private static final String FIND_USER_ORDERS = "SELECT userid, orders.id as ordersid, orders.price as orderprice, date,order_status," +
            "dish_quantity,name,size,dish.price as dishprice,clientinfo,staffinfo,picture,dishstatus,dish.id as dishid,ingridient_name," +
            "ingridient_quantity FROM orders JOIN order_dish on orders.id = order_dish.orderid JOIN dish " +
            "on order_dish.dish_name = dish.name JOIN ingridient on dish.name = ingridient.dish_name where userid=? and date BETWEEN ? and ?;";

    private static final String UPDATE_ORDER_STATUS = "UPDATE orders SET order_status=CAST(? AS order_status) WHERE id=?";

    private static final String UPDATE_STATUS_POINT = "UPDATE  users SET status_point=?, user_status=CAST(? AS user_status) WHERE id=?";

    @Override
    public int createOrder(Order order, int userId) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(CREATE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setTimestamp(2, new Timestamp(order.getDate().getTime()));
            ps.setBigDecimal(3, order.getPrice());
            ps.setBigDecimal(4, order.getDiscount());
            ps.setString(5, order.getStatus().name());
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Update failed. Try again!");
            } else {
                ResultSet resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {//todo
                    int orderId = resultSet.getInt(2);//todo as in table!!!! wtf?
                    Map<Dish, Integer> dishes = order.getDishMap();
                    int counter = 1;
                    preparedStatement = connection.prepareStatement(INSERT_DISH_QUANTITY);
                    for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
                        Dish dish = entry.getKey();
                        Integer quantity = entry.getValue();
                        preparedStatement.setInt(1, orderId);
                        preparedStatement.setString(2, dish.getName());
                        preparedStatement.setInt(3, quantity);
                        counter++;
                        affectedRows = preparedStatement.executeUpdate();
                    }
                    connection.commit();
                }
                resultSet.close();
            }
            if (affectedRows == 0) {
                logger.error("Creation failed. Try again!");
            } else {
                logger.info("Order created");
            }
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Creation failed. Something wrong with database", e);
            throw new DaoException(e);
        } finally {
            close(connection);
            close(ps);
            close(preparedStatement);
        }
        return affectedRows;
    }

    @Override
    public Optional<Order> findOrderById(int idx) throws DaoException {
        Optional<Order> optionalOrder=Optional.empty();
        Order order = null;
        Dish dish = null;
        Map<Dish, Integer> dishMap = null;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(FIND_ORDER_BY_ID);
            ps.setInt(1, idx);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            dishMap = new HashMap<>();
            int id = resultSet.getInt("ordersid");
            int userId = resultSet.getInt("userid");
            BigDecimal price = resultSet.getBigDecimal("orderprice");
            BigDecimal discount = resultSet.getBigDecimal("discount");
            Date date = resultSet.getTimestamp("date");
            OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
            do {
                int dish_quantity = resultSet.getInt("dish_quantity");
                dish = convertResultSet(resultSet);
                dishMap.put(dish, dish_quantity);
            }
            while (resultSet.next() && resultSet.getInt("ordersid") == id);
            order = new Order(id, userId, price, discount, date, orderStatus, dishMap);
            optionalOrder=Optional.of(order);
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        } finally {
            close(connection);
            close(ps);
        }
        return optionalOrder;
    }

    @Override
    public List<Order> findOrders(Date dateFrom, Date dateTo) throws DaoException {
        Order order = null;
        Dish dish = null;
        Map<Dish, Integer> dishMap = null;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(FIND_ALL_ORDERS);
            ps.setTimestamp(1, new Timestamp(dateFrom.getTime()));
            ps.setTimestamp(2, new Timestamp(dateTo.getTime()));
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                dishMap = new HashMap<>();
                int id = resultSet.getInt("ordersid");
                int userId = resultSet.getInt("userid");
                BigDecimal price = resultSet.getBigDecimal("orderprice");
                BigDecimal discount = resultSet.getBigDecimal("discount");
                Date date = resultSet.getTimestamp("date");
                OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
                do {
                    int dish_quantity = resultSet.getInt("dish_quantity");
                    dish = convertResultSet(resultSet);
                    dishMap.put(dish, dish_quantity);
                }
                while (resultSet.next() && resultSet.getInt("ordersid") == id);
                order = new Order(id, userId, price, discount, date, orderStatus, dishMap);
                orders.add(order);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        } finally {
            close(connection);
            close(ps);
        }
        return orders;
    }

    @Override
    public List<Order> findOrders(int userId, Date dateFrom, Date dateTo) throws DaoException {
        Order order = null;
        Dish dish = null;
        Map<Dish, Integer> dishMap = null;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(FIND_USER_ORDERS);
            ps.setInt(1, userId);
            ps.setTimestamp(2, new Timestamp(dateFrom.getTime()));
            ps.setTimestamp(3, new Timestamp(dateFrom.getTime()));

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                dishMap = new HashMap<>();
                int id = resultSet.getInt("ordersid");
                BigDecimal price = resultSet.getBigDecimal("orderprice");
                BigDecimal discount = resultSet.getBigDecimal("discount");
                Date date = resultSet.getTimestamp("date");
                OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
                do {
                    int dish_quantity = resultSet.getInt("dish_quantity");
                    dish = convertResultSet(resultSet);
                    dishMap.put(dish, dish_quantity);
                }
                while (resultSet.next() && resultSet.getInt("ordersid") == id);
                order = new Order(id, userId, price, discount, date, orderStatus, dishMap);
                orders.add(order);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        } finally {
            close(connection);
            close(ps);
        }
        return orders;
    }

    @Override
    public int updateOrderStatus(int orderId, OrderStatus orderStatus) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        int affectedRows = 0;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(UPDATE_ORDER_STATUS);
            {
                ps.setString(1, valueOf(orderStatus));
                ps.setInt(2, orderId);
            }
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Order status update failed. Try again!");
            } else {
                logger.info("Order status updated");
            }
        } catch (SQLException e) {
            logger.error("Order status update failed. Something wrong with database", e);
            throw new DaoException(e);
        } finally {
            close(connection);
            close(ps);
        }
        return affectedRows;
    }

    @Override
    public int updateOrderStatusPoint(int orderId, OrderStatus orderStatus, int userId, BigDecimal statusPoint, UserStatus newUserStatus) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_ORDER_STATUS);
            ps.setString(1, valueOf(orderStatus));
            ps.setInt(2, orderId);
             affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Order status update failed. Try again!");
            } else {
                logger.info("Order status updated");
            }
            ps.executeUpdate();
            preparedStatement = connection.prepareStatement(UPDATE_STATUS_POINT);
            preparedStatement.setBigDecimal(1, statusPoint);
            preparedStatement.setString(2, valueOf(newUserStatus));
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error("Update failed. Something wrong with database", e);
            throw new DaoException(e);
        } finally {
            close(connection);
            close(ps);
            close(preparedStatement);
        }
        return affectedRows;
    }

    private Dish convertResultSet(ResultSet resultSet) throws SQLException {
        Dish dish = null;
        int id = resultSet.getInt("dishid");
        String name = resultSet.getString("name");
        String size = resultSet.getString("size");
        BigDecimal price = resultSet.getBigDecimal("dishprice");
        String clientInfo = resultSet.getString("clientinfo");
        String staffInfo = resultSet.getString("staffinfo");
        String picture = resultSet.getString("picture");
        DishStatus status = DishStatus.valueOf(resultSet.getString("dishstatus"));
        List<String> names = new ArrayList<>();
        List<String> quantity = new ArrayList<>();
        do {
            names.add(resultSet.getString("ingridient_name"));
            quantity.add(resultSet.getString("ingridient_quantity"));
        }
        while (resultSet.next() && resultSet.getInt("dishid") == id);
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i) != null && quantity.get(i) != null) {
                Ingredient ingredient = new Ingredient(names.get(i), quantity.get(i));
                ingredients.add(ingredient);
            }
        }
        dish = new Dish(id, name, size, price, clientInfo, staffInfo, picture, status, ingredients);
        return dish;
    }
}
