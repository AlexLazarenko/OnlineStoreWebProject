package edu.epam.web.model.dao;

import edu.epam.web.model.entity.Dish;
import edu.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface DishDao extends BaseDao{
    int createDish(Dish dish) throws DaoException;

    Optional<Dish> findDishById(int idx) throws DaoException;

    List<Dish> findDishes() throws DaoException;

    int updateDish(Dish newDish) throws DaoException;

    int updatePicture(int id, String picture) throws DaoException;

    int deleteDish(int idx) throws DaoException;

}
