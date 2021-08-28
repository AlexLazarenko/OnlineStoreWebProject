package edu.epam.web.service;

import edu.epam.web.dao.impl.DishDaoImpl;
import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.User;
import edu.epam.web.exception.DaoException;
import edu.epam.web.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishDaoService {
    private static final Logger logger = LogManager.getLogger(DishDaoService.class);
    private final DishDaoImpl dishDaoImpl = new DishDaoImpl();

    public int createDish(Dish dish) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows=dishDaoImpl.createDish(dish);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public List<Dish> findDishes() throws ServiceException {
        List<Dish> dishes;
        try {
            dishes = dishDaoImpl.findDishes();
            return dishes;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<Dish> findDishById(int id) throws ServiceException {
        try {
            Optional<Dish> dish = dishDaoImpl.findDishById(id);
            return dish;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateDishImage(int dishId, String image) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = dishDaoImpl.updatePicture(dishId, image);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateDish(Dish newDish) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = dishDaoImpl.updateDish(newDish);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
