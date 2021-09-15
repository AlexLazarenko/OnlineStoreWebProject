package edu.epam.web.utility;

import edu.epam.web.model.entity.Dish;

import java.util.Map;
import java.util.Optional;

public class DishOrderUtil {
    public static Map<Dish, Integer> putDish(Map<Dish, Integer> dishMap, Optional<Dish> optionalDish,int dishQuantity){
        if (dishMap.containsKey(optionalDish.get())) {
            dishMap.put(optionalDish.get(), dishMap.get(optionalDish.get()) + dishQuantity);
        } else {
            dishMap.put(optionalDish.get(), dishQuantity);
        }
        return dishMap;
    }
}
