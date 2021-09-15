package edu.epam.web.model.factory;

import edu.epam.web.model.entity.*;
import edu.epam.web.validator.DishValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class DishFactory {
    private static final Logger logger = LogManager.getLogger(DishFactory.class);
    private final DishValidator validator = new DishValidator();
    //todo optional
    public Dish createDish(int id, String name, String size, BigDecimal price, String clientInfo, String staffInfo,
                           String dishImage, DishStatus dishStatus, List<Ingredient> ingredientList)   {
        Dish dish = null;
        if (validator.isDish(name, size, clientInfo, staffInfo, ingredientList)) {
            dish = new Dish(id, name, size, price, clientInfo, staffInfo, dishImage,
                    dishStatus, ingredientList);
        }
        logger.info("Dish created " + dish);
        return dish;
    }

}
