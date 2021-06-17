package edu.epam.web.service;

import edu.epam.web.dao.impl.DishDaoImpl;
import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.Dish;
import edu.epam.web.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DishDaoService {
    private static final Logger logger = LogManager.getLogger(DishDaoService.class);
    private final DishDaoImpl dishDaoImpl = new DishDaoImpl();

    public void createDish(Dish dish) {
        dishDaoImpl.createDish(dish);
    }

    public List<Dish> findDishes() {
        return dishDaoImpl.findDishes();
    }

    public Dish findDishById(int id) {
        return dishDaoImpl.findDishById(id);
    }

    public void updateDishImage(int dishId, byte[] image) {
        dishDaoImpl.updatePicture(dishId, image);
    }
}
