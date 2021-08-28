package edu.epam.web.dao.impl;

import edu.epam.web.connection.ConnectionPool;
import edu.epam.web.dao.DishDao;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.DishStatus;
import edu.epam.web.entity.Ingredient;
import edu.epam.web.entity.User;
import edu.epam.web.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DishDaoImpl implements DishDao {

    private static final Logger logger = LogManager.getLogger(edu.epam.web.dao.impl.DishDaoImpl.class);

    private final ConnectionPool pool = ConnectionPool.getInstance();

    private final List<Dish> dishes = new ArrayList<>();

    private static final String CREATE_DISH = "INSERT INTO dish (name, size, price,clientinfo,staffinfo, picture, dishstatus) " +
            "VALUES (?,?,?,?,?,?,CAST(? AS dish_status));";

    private static final String CREATE_INGREDIENT = "INSERT INTO ingridient (dish_name, ingridient_name, ingridient_quantity) VALUES (?,?,?);";

    private static final String FIND_DISH_BY_ID = "SELECT id, name, size, price,clientinfo,staffinfo, picture, dishstatus," +
            " ingridient_name, ingridient_quantity FROM dish JOIN ingridient on dish.name = ingridient.dish_name where id=?";

    private static final String FIND_DISHES = "SELECT id, name, size, price,clientinfo,staffinfo, picture, dishstatus," +
            " ingridient_name, ingridient_quantity FROM dish JOIN ingridient on dish.name = ingridient.dish_name;";

    private static final String UPDATE_DISH = "UPDATE dish SET name=?,size=?,price=?,clientinfo=?,staffinfo=?,picture=?," +
            "dishstatus=CAST(? AS dish_status) WHERE id=?";

    private static final String UPDATE_INGREDIENT = "UPDATE ingridient SET ingridient_name=?, ingridient_quantity=? where dish_name=? ";

    private static final String UPDATE_PICTURE = "UPDATE dish SET picture=? WHERE id=?";

    private static final String DELETE_DISH = "DELETE FROM dish where id=?";


    @Override

    public int createDish(Dish dish) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(CREATE_DISH, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, dish.getName());
            ps.setString(2, dish.getSize());
            ps.setBigDecimal(3, dish.getPrice());
            ps.setString(4, dish.getClientInfo());
            ps.setString(5, dish.getStaffInfo());
            ps.setString(6, dish.getDishImage());
            ps.setString(7, dish.getStatus().name());
           affectedRows= ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Update failed. Try again!");
            } else {
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                List<Ingredient> ingredients = dish.getIngredientList();
                preparedStatement = connection.prepareStatement(CREATE_INGREDIENT);
                for (Ingredient ingredient : ingredients) {
                    preparedStatement.setString(1, dish.getName());
                    preparedStatement.setString(2, ingredient.getName());
                    preparedStatement.setString(3, ingredient.getSize());
                    affectedRows=preparedStatement.executeUpdate();
                }
                connection.commit();
            }
                resultSet.close();
            }
            if (affectedRows == 0) {
                logger.error("Creation failed. Try again!");
            } else {
                logger.info("Dish created");
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
    public Optional<Dish> findDishById(int idx) throws DaoException {
        Optional<Dish> optionalDish = Optional.empty();
        Dish dish = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_DISH_BY_ID);
        ) {
            ps.setInt(1, idx);
            ResultSet rs = ps.executeQuery();
            rs.next();
            dish = convertResultSet(rs);
            optionalDish=Optional.of(dish);
            rs.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return optionalDish;
    }


    @Override
    public List<Dish> findDishes() throws DaoException {
        Dish dish = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_DISHES);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dish = convertResultSet(rs);
                dishes.add(dish);
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return dishes;
    }

    @Override
    public int updateDish(Dish newDish) throws DaoException { //todo transanction
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        int id = newDish.getId();
        String name = newDish.getName();
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_DISH);
            ps.setString(1, newDish.getName());
            ps.setString(2, newDish.getSize());
            ps.setBigDecimal(3, newDish.getPrice());
            ps.setString(4, newDish.getClientInfo());
            ps.setString(5, newDish.getStaffInfo());
            ps.setString(6, newDish.getDishImage());
            ps.setString(7, newDish.getStatus().name());
            ps.setInt(8, id);
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Update failed. Try again!");
            } else {
                List<Ingredient> ingredients = newDish.getIngredientList();
                preparedStatement = connection.prepareStatement(UPDATE_INGREDIENT);
                for (Ingredient ingredient : ingredients) {
                    preparedStatement.setString(1, ingredient.getName());
                    preparedStatement.setString(2, ingredient.getSize());
                    preparedStatement.setString(3, name);
                    affectedRows = preparedStatement.executeUpdate();
                }
                connection.commit();
            }
            if (affectedRows == 0) {
                logger.error("Update failed. Try again!");
            } else {
                logger.info("Dish updated");
            }
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

    @Override
    public int updatePicture(int id, String picture) throws DaoException {
        int affectedRows = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PICTURE);
        ) {
            {
                ps.setString(1, picture);
                ps.setInt(2, id);
            }
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.error("Picture update failed. Try again!");
            } else {
                logger.info("Picture updated");
            }
        } catch (SQLException e) {
            logger.error("Picture update failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
    }

    @Override
    public int deleteDish(int idx) throws DaoException {
        int affectedRows = 0;
        try (
                Connection connection = pool.getConnection();
                PreparedStatement ps = connection.prepareStatement(DELETE_DISH);
        ) {
            ps.setInt(1, idx);
            affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                logger.info("Dish with this id not exist");
            } else {
                logger.info("Dish deleted");
            }
        } catch (SQLException e) {
            logger.error("Failed. Something wrong with database", e);
            throw new DaoException(e);
        }
        return affectedRows;
    }


    private Dish convertResultSet(ResultSet resultSet) throws SQLException {
        Dish dish = null;
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String size = resultSet.getString("size");
        BigDecimal price = resultSet.getBigDecimal("price");
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
        while (resultSet.next() && resultSet.getInt("id") == id);
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



