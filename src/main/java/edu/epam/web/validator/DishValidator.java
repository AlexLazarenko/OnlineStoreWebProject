package edu.epam.web.validator;

import edu.epam.web.model.entity.Ingredient;

import java.util.List;

public class DishValidator implements StringValidator {

    private static final int MIN_INGREDIENT_LIST_LENGTH = 1;

    private static final int MIN_STRING_LENGTH = 2;

    private static final int MAX_CLIENT_INFO_LENGTH = 200;

    private static final int MAX_STAFF_INFO_LENGTH = 300;

    private static final int MAX_DISH_NAME_LENGTH = 25;

    private static final int MAX_DISH_SIZE_LENGTH = 25;

    public boolean isDish(String name, String size, String clientInfo, String staffInfo, List<Ingredient> ingredientList) {
        return isName(name) && isSize(size) && isClientInfo(clientInfo) && isStaffInfo(staffInfo)
                && isIngredientList(ingredientList);
    }

    private boolean isClientInfo(String clientInfo) {
        return isNotEmptyOrNull(clientInfo) && isStringLengthCorrect(clientInfo, MIN_STRING_LENGTH, MAX_CLIENT_INFO_LENGTH);
    }

    private boolean isName(String name) {

        return isNotEmptyOrNull(name) && isStringLengthCorrect(name, MIN_STRING_LENGTH, MAX_DISH_NAME_LENGTH);
    }

    private boolean isSize(String size) {

        return isNotEmptyOrNull(size) && isStringLengthCorrect(size, MIN_STRING_LENGTH, MAX_DISH_SIZE_LENGTH);
    }

    private boolean isStaffInfo(String staffInfo) {
        return isNotEmptyOrNull(staffInfo) && isStringLengthCorrect(staffInfo, MIN_STRING_LENGTH, MAX_STAFF_INFO_LENGTH);
    }

    private boolean isIngredientList(List<Ingredient> ingredientList) {
        boolean isIngredientList = true;
        if (ingredientList.size() >= MIN_INGREDIENT_LIST_LENGTH) {
            for (Ingredient ingridient : ingredientList) {
                if (!isNotEmptyOrNull(ingridient.getName()) || !isNotEmptyOrNull(ingridient.getSize())) {
                    isIngredientList = false;
                }
            }
        } else isIngredientList = false;
        return isIngredientList;
    }

}
