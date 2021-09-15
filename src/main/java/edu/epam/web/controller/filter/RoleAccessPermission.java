package edu.epam.web.controller.filter;

import edu.epam.web.command.CommandAction;

import java.util.Set;

/**
 * The enum Role permission.
 */

public enum RoleAccessPermission {

    /**
     * Admin role permission.
     */
    ADMIN(Set.of(
            CommandAction.ACTIVATE_ACCOUNT,
            CommandAction.ADD_DISH,
            CommandAction.ADD_DISH_ORDER,
            CommandAction.ADD_DISH_RESULT,
            CommandAction.CHANGE_LANGUAGE,
            CommandAction.CHANGE_PASSWORD,
            CommandAction.CHANGE_PASSWORD_RESULT,
            CommandAction.DELETE_USER,
            CommandAction.FORGOT_PASSWORD,
            CommandAction.FORGOT_PASSWORD_RESULT,
            CommandAction.HOME,
            CommandAction.LOGOUT,
            CommandAction.LOGIN,
            CommandAction.LOGIN_RESULT,
            CommandAction.MAKE_ORDER,
            CommandAction.UPDATE_ACCOUNT_STATUS,
            CommandAction.UPDATE_USER,
            CommandAction.UPDATE_USER_ROLE,
            CommandAction.UPDATE_RESULT_USER,
            CommandAction.UPDATE_DISH,
            CommandAction.UPDATE_DISH_RESULT,
            CommandAction.UPDATE_ORDER_STATUS,
            CommandAction.UPLOAD_USER_AVATAR,
            CommandAction.UPLOAD_USER_AVATAR_RESULT,
            CommandAction.UPLOAD_DISH_IMAGE,
            CommandAction.UPLOAD_DISH_IMAGE_RESULT,
            CommandAction.REGISTRATION,
            CommandAction.REGISTRATION_RESULT,
            CommandAction.SHOW_USER,
            CommandAction.SHOW_ALL_USERS,
            CommandAction.SHOW_ALL_ORDERS,  //todo
            CommandAction.SHOW_USER_ORDERS,
            CommandAction.SHOW_USER_ORDERS_BY_DATE,
            CommandAction.SHOW_USER_ORDERS_BY_ID_DATE,
            CommandAction.SHOW_ORDER_BY_ID,
            CommandAction.SHOW_DISH,
            CommandAction.SHOW_MENU,
            CommandAction.SHOW_SHOPPING_CART,
            CommandAction.UPDATE_SHOPPING_CART
    )),

    STAFF(Set.of(
            CommandAction.ACTIVATE_ACCOUNT,
            CommandAction.ADD_DISH,
            CommandAction.ADD_DISH_ORDER,
            CommandAction.ADD_DISH_RESULT,
            CommandAction.CHANGE_LANGUAGE,
            CommandAction.CHANGE_PASSWORD,
            CommandAction.CHANGE_PASSWORD_RESULT,
            CommandAction.FORGOT_PASSWORD,
            CommandAction.FORGOT_PASSWORD_RESULT,
            CommandAction.HOME,
            CommandAction.LOGOUT,
            CommandAction.LOGIN,
            CommandAction.LOGIN_RESULT,
            CommandAction.MAKE_ORDER,
            CommandAction.UPDATE_ACCOUNT_STATUS,
            CommandAction.UPDATE_USER,
            CommandAction.UPDATE_RESULT_USER,
            CommandAction.UPDATE_DISH,
            CommandAction.UPDATE_DISH_RESULT,
            CommandAction.UPDATE_ORDER_STATUS,
            CommandAction.UPLOAD_USER_AVATAR,
            CommandAction.UPLOAD_USER_AVATAR_RESULT,
            CommandAction.UPLOAD_DISH_IMAGE,
            CommandAction.UPLOAD_DISH_IMAGE_RESULT,
            CommandAction.REGISTRATION,
            CommandAction.REGISTRATION_RESULT,
            CommandAction.SHOW_USER,
            CommandAction.SHOW_ALL_USERS,
            CommandAction.SHOW_ALL_ORDERS,  //todo
            CommandAction.SHOW_USER_ORDERS,
            CommandAction.SHOW_USER_ORDERS_BY_DATE,
            CommandAction.SHOW_USER_ORDERS_BY_ID_DATE,
            CommandAction.SHOW_ORDER_BY_ID,
            CommandAction.SHOW_DISH,
            CommandAction.SHOW_MENU,
            CommandAction.SHOW_SHOPPING_CART,
            CommandAction.UPDATE_SHOPPING_CART
    )),

    /**
     * Client role permission.
     */
    CLIENT(Set.of(
            CommandAction.ACTIVATE_ACCOUNT,
            CommandAction.ADD_DISH_ORDER,
            CommandAction.ADD_DISH_RESULT,
            CommandAction.CHANGE_LANGUAGE,
            CommandAction.CHANGE_PASSWORD,
            CommandAction.CHANGE_PASSWORD_RESULT,
            CommandAction.FORGOT_PASSWORD,
            CommandAction.FORGOT_PASSWORD_RESULT,
            CommandAction.HOME,
            CommandAction.LOGOUT,
            CommandAction.LOGIN,
            CommandAction.LOGIN_RESULT,
            CommandAction.MAKE_ORDER,
            CommandAction.UPDATE_USER,
            CommandAction.UPDATE_RESULT_USER,
            CommandAction.UPDATE_ORDER_STATUS,
            CommandAction.UPLOAD_USER_AVATAR,
            CommandAction.UPLOAD_USER_AVATAR_RESULT,
            CommandAction.REGISTRATION,
            CommandAction.REGISTRATION_RESULT,
            CommandAction.SHOW_USER,
            CommandAction.SHOW_ALL_ORDERS,  //todo
            CommandAction.SHOW_USER_ORDERS,
            CommandAction.SHOW_USER_ORDERS_BY_DATE,
            CommandAction.SHOW_USER_ORDERS_BY_ID_DATE,
            CommandAction.SHOW_ORDER_BY_ID,
            CommandAction.SHOW_DISH,
            CommandAction.SHOW_MENU,
            CommandAction.SHOW_SHOPPING_CART,
            CommandAction.UPDATE_SHOPPING_CART
    )),

    /**
     * Guest role permission.
     */
    GUEST(Set.of(
            CommandAction.CHANGE_LANGUAGE,
            CommandAction.LOGIN,
            CommandAction.REGISTRATION,
            CommandAction.HOME,
            CommandAction.SHOW_MENU,
            CommandAction.LOGIN_RESULT,
            CommandAction.REGISTRATION_RESULT,
            CommandAction.FORGOT_PASSWORD,
            CommandAction.FORGOT_PASSWORD_RESULT
    ));
    private final Set<String> commands;

    RoleAccessPermission(Set<String> commandNames) {
        this.commands = commandNames;
    }

    /**
     * Gets commands.
     *
     * @return the commands
     */
    public Set<String> getCommands() {
        return commands;
    }
}
