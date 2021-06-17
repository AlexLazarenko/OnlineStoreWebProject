package edu.epam.web.dao.impl;

import edu.epam.web.connection.ConnectionPool;
import edu.epam.web.dao.DishDao;
import edu.epam.web.dao.UserDao;
import edu.epam.web.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.lang.String.valueOf;

public class DishDaoImpl implements DishDao {
    private static final Logger logger = LogManager.getLogger(edu.epam.web.dao.impl.DishDaoImpl.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private final List<Dish> dishes = new ArrayList<>();
 /*   Connection connection = ...;
// Сброс автофиксации
connection.setAutoCommit(false);
    // Первая транзакция
    PreparedStatement updateSales = connection.prepareStatement(
            "UPDATE COFFEES SET SALES = ? WHERE COF_NAME LIKE ?");
updateSales.setInt(1, 50);
updateSales.setString(2, "Colombian");
updateSales.executeUpdate();

    // Вторая транзакция
    PreparedStatement updateTotal = connection.prepareStatement(
            "UPDATE COFFEES SET TOTAL = TOTAL + ? WHERE COF_NAME LIKE ?");
updateTotal.setInt(1, 50);
updateTotal.setString(2, "Colombian");
updateTotal.executeUpdate();
// Завершение транзакции
connection.commit();
// Восстановление по умолчанию
connection.setAutoCommit(true);*/


    @Override
    public void createDish(Dish dish) {
        Connection connection = pool.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Transaction fail", e);
        }
        String sqlStatement = "INSERT INTO dish (name, size, price,clientinfo,staffinfo, picture, dishstatus) " +
                "VALUES (?,?,?,?,?,?,CAST(? AS dish_status));";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);
            {
                ps.setString(1, dish.getName());
                ps.setString(2, dish.getSize());
                ps.setBigDecimal(3, dish.getPrice());
                ps.setString(4, dish.getClientInfo());
                ps.setString(5, dish.getStaffInfo());
                ps.setBytes(6, dish.getDishImage());
                ps.setString(7, dish.getStatus().name());
            }
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {//todo
                int dishId = resultSet.getInt(8);
                List<Ingredient> ingredients = dish.getIngredientList();
                String statement = "INSERT INTO ingridient (dishid, ingridient_name, ingridient_quantity) " +
                        "VALUES (?,?,?);";
                for (Ingredient ingredient : ingredients) {
                    PreparedStatement preparedStatement = connection.prepareStatement(statement);
                    {
                        preparedStatement.setInt(1, dishId);
                        preparedStatement.setString(2, ingredient.getName());
                        preparedStatement.setString(3, ingredient.getSize());
                    }
                    preparedStatement.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
                ps.close();
            }
        } catch (SQLException e) {
            logger.error("Creation failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
    }


    @Override
    public Dish findDishById(int idx) {
        Dish dish = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id, name, size, price,clientinfo,staffinfo," +
                    " picture, dishstatus, ingridient_name, ingridient_quantity FROM dish" +
                    "  JOIN ingridient on dish.id = ingridient.dishid where id=?");
            ps.setInt(1, idx);
            ResultSet rs = ps.executeQuery();
            rs.next();
            dish = convertResultSet(rs);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dish;
    }


    @Override
    public List<Dish> findDishes() {
        Dish dish = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id, name, size, price,clientinfo,staffinfo," +
                    " picture, dishstatus, ingridient_name, ingridient_quantity FROM dish  JOIN ingridient" +
                    " on dish.id = ingridient.dishid;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dish = convertResultSet(rs);
                dishes.add(dish);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }

    @Override
 /* UPDATE vehicles_vehicle AS v
    SET price = s.price_per_vehicle
    FROM shipments_shipment AS s
    WHERE v.shipment_id = s.id   */
    public int updateDish(Dish newDish) { //todo transanction
        Connection connection = pool.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Transaction fail", e);
        }
        int id = newDish.getId();
        String sqlStatement = "UPDATE dish SET name=?,size=?,price=?," +
                "clientinfo=?,staffinfo=?,picture=?,dishstatus=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            {
                ps.setString(1, newDish.getName());
                ps.setString(2, newDish.getSize());
                ps.setBigDecimal(3, newDish.getPrice());
                ps.setString(4, newDish.getClientInfo());
                ps.setString(5, newDish.getStaffInfo());
                ps.setBytes(6, newDish.getDishImage());
                ps.setString(7, newDish.getStatus().name());
                ps.setInt(8, id);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Update failed. Try again!");
            } else {
                List<Ingredient> ingredients = newDish.getIngredientList();
                String statement = "UPDATE ingridient SET ingridient_name=?, ingridient_quantity=? where dishid=? ";
                for (Ingredient ingredient : ingredients) {
                    PreparedStatement preparedStatement = connection.prepareStatement(statement);
                    {
                        preparedStatement.setString(1, ingredient.getName());
                        preparedStatement.setString(2, ingredient.getSize());
                        preparedStatement.setInt(3, id);
                    }
                    affectedRows = preparedStatement.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
            }
            if (affectedRows == 0) {
                logger.error("Update failed. Try again!");
            } else {
                logger.info("Dish updated");
            }
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            logger.error("Update failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return 0;
    }

    @Override
    public int updatePicture(int id, byte[] picture) {
        String sqlStatement = "UPDATE dish SET picture=? WHERE id=?";
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            {
                ps.setBytes(1, picture);
                ps.setInt(2, id);
            }
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Picture update failed. Try again!");
            } else {
                logger.info("Picture updated");
            }
            ps.close();
            return affectedRows;
        } catch (SQLException e) {
            logger.error("Picture update failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
        return 0;
    }

    @Override
    public void deleteDish(int idx) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM dish where id=?");
            ps.setInt(1, idx);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.info("Dish with this id not exist");
            } else {
                logger.info("Dish deleted");
            }
            ps.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
        } finally {
            pool.releaseConnection(connection);
        }
    }


    private Dish convertResultSet(ResultSet resultSet) throws SQLException {
        Dish dish = null;
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String size = resultSet.getString("size");
        BigDecimal price = resultSet.getBigDecimal("price");
        String clientInfo = resultSet.getString("clientinfo");
        String staffInfo = resultSet.getString("staffinfo");
        byte[] picture = resultSet.getBytes("picture");
        DishStatus status = DishStatus.valueOf(resultSet.getString("dishstatus"));
        List<String> names = new ArrayList<>();
        List<String> quantity = new ArrayList<>();
        do {
            names.add(resultSet.getString("ingridient_name"));
            quantity.add(resultSet.getString("ingridient_quantity"));
        } while (resultSet.next());
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



