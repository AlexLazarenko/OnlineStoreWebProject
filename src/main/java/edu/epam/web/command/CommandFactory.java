package edu.epam.web.command;

import edu.epam.web.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory commandFactory;

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        if (commandFactory == null) {
            commandFactory = new CommandFactory();
        }
        return commandFactory;
    }

    static Map<String, Command> commandMap = new HashMap<String, Command>();

    static {
        commandMap.put(CommandAction.ACTIVATE_ACCOUNT,new ActivateAccountCommand());
        commandMap.put(CommandAction.ADD_DISH,new AddDishCommand());
        commandMap.put(CommandAction.ADD_DISH_ORDER,new AddDishOrderCommand());
        commandMap.put(CommandAction.ADD_DISH_RESULT,new AddDishResultCommand());
        commandMap.put(CommandAction.CHANGE_LANGUAGE,new ChangeLanguageCommand());
        commandMap.put(CommandAction.CHANGE_PASSWORD,new ChangePasswordCommand());
        commandMap.put(CommandAction.CHANGE_PASSWORD_RESULT,new ChangePasswordResultCommand());
        commandMap.put(CommandAction.DELETE_USER, new DeleteUserCommand());
        commandMap.put(CommandAction.FORGOT_PASSWORD, new ForgotPasswordCommand());
        commandMap.put(CommandAction.FORGOT_PASSWORD_RESULT,new ForgotPasswordResultCommand());
        commandMap.put(CommandAction.HOME, new HomeCommand());
        commandMap.put(CommandAction.LOGOUT, new LogoutCommand());
        commandMap.put(CommandAction.LOGIN, new LoginCommand());
        commandMap.put(CommandAction.LOGIN_RESULT,new LoginResultCommand());
        commandMap.put(CommandAction.MAKE_ORDER,new MakeOrderCommand());
        commandMap.put(CommandAction.UPDATE_ACCOUNT_STATUS,new UpdateAccountStatusCommand());
        commandMap.put(CommandAction.UPDATE_USER, new UpdateUserCommand());
        commandMap.put(CommandAction.UPDATE_USER_ROLE,new UpdateUserRoleCommand());
        commandMap.put(CommandAction.UPDATE_RESULT_USER, new UpdateResultUserCommand());
        commandMap.put(CommandAction.UPDATE_DISH, new UpdateDishCommand());
        commandMap.put(CommandAction.UPDATE_DISH_RESULT, new UpdateDishResultCommand());
        commandMap.put(CommandAction.UPDATE_ORDER_STATUS,new UpdateOrderStatusCommand());
        commandMap.put(CommandAction.UPLOAD_USER_AVATAR, new UploadUserAvatarCommand());
        commandMap.put(CommandAction.UPLOAD_USER_AVATAR_RESULT, new UploadUserAvatarResultCommand());
        commandMap.put(CommandAction.UPLOAD_DISH_IMAGE,new UploadDishImageCommand());
        commandMap.put(CommandAction.UPLOAD_DISH_IMAGE_RESULT,new UploadDishImageResultCommand());
        commandMap.put(CommandAction.REGISTRATION, new RegistrationCommand());
        commandMap.put(CommandAction.REGISTRATION_RESULT,new RegistrationResultCommand());
        commandMap.put(CommandAction.SHOW_USER, new ShowUserCommand());
        commandMap.put(CommandAction.SHOW_ALL_USERS, new ShowAllUsersCommand());
        commandMap.put(CommandAction.SHOW_ALL_ORDERS,new ShowAllOrdersCommand());
        commandMap.put(CommandAction.SHOW_USER_ORDERS,new ShowUserOrdersCommand());
        commandMap.put(CommandAction.SHOW_USER_ORDERS_BY_DATE,new ShowUserOrdersByDateCommand());
        commandMap.put(CommandAction.SHOW_USER_ORDERS_BY_ID_DATE,new ShowUserOrdersByIdDateCommand());
        commandMap.put(CommandAction.SHOW_ORDER_BY_ID,new ShowOrderByIdCommand());
        commandMap.put(CommandAction.SHOW_DISH,new ShowDishCommand());
        commandMap.put(CommandAction.SHOW_MENU,new ShowMenuCommand());
        commandMap.put(CommandAction.SHOW_SHOPPING_CART,new ShowShoppingCartCommand());
        commandMap.put(CommandAction.UPDATE_SHOPPING_CART,new UpdateShoppingCartCommand());
    }

    public Command getCommand(String action) {
        return commandMap.get(action);

    }
}
