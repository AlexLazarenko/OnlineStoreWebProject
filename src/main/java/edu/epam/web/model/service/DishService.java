package edu.epam.web.model.service;

import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.entity.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {

     int createDish(Dish dish) throws ServiceException;


     List<Dish> findDishes() throws ServiceException ;


     Optional<Dish> findDishById(int id) throws ServiceException ;


     int updateDishImage(int dishId, String image) throws ServiceException ;


     int updateDish(Dish newDish) throws ServiceException ;

}
