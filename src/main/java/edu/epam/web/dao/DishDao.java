package edu.epam.web.dao;

import edu.epam.web.entity.Dish;

import java.util.List;

public interface DishDao {
    void createDish(Dish dish);

    Dish findDishById(int idx);

    List<Dish> findDishes();

    int updateDish(Dish newDish);

    int updatePicture(int id, byte[] picture);

    void deleteDish(int idx);

}
