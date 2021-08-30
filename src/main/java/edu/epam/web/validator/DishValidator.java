package edu.epam.web.validator;

import edu.epam.web.entity.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DishValidator {
    private static final Logger logger = LogManager.getLogger(DishValidator.class);

    public boolean isDish(String name, String size, String clientInfo, String staffInfo, List<Ingredient> ingredientList)  {
        return isName(name) && isSize(size) && isClientInfo(clientInfo) && isStaffInfo(staffInfo)
                && isIngredientList(ingredientList);
    }

    private boolean isClientInfo(String clientInfo) {
        return true;
    }

    private boolean isName(String name) {
        return true;
    }

    private boolean isSize(String size) {
        return true;
    }

    private boolean isStaffInfo(String staffInfo){return true;}

    private boolean isIngredientList(List<Ingredient> ingredientList){return true;}

}
