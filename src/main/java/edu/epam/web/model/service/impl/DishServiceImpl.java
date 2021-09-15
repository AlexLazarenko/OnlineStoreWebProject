package edu.epam.web.model.service.impl;

import edu.epam.web.model.dao.DishDao;
import edu.epam.web.model.dao.impl.DishDaoImpl;
import edu.epam.web.model.entity.Dish;
import edu.epam.web.exception.DaoException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.DishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class DishServiceImpl implements DishService {
    private static final Logger logger = LogManager.getLogger(DishServiceImpl.class);
    private final DishDao dishDao = new DishDaoImpl();

    public int createDish(Dish dish) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows=dishDao.createDish(dish);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public List<Dish> findDishes() throws ServiceException {
        List<Dish> dishes;
        try {
            dishes = dishDao.findDishes();
            return dishes;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public Optional<Dish> findDishById(int id) throws ServiceException {
        try {
            Optional<Dish> dish = dishDao.findDishById(id);
            return dish;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateDishImage(int dishId, String image) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = dishDao.updatePicture(dishId, image);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public int updateDish(Dish newDish) throws ServiceException {
        int affectedRows = 0;
        try {
            affectedRows = dishDao.updateDish(newDish);
            return affectedRows;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
