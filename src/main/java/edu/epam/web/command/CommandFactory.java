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
        commandMap.put("activateAccount",new ActivateAccountCommand());
        commandMap.put("addDish",new AddDishCommand());
        commandMap.put("addDishOrder",new AddDishOrderCommand());
        commandMap.put("addDishResult",new AddDishResultCommand());
        commandMap.put("changePassword",new ChangePasswordCommand());
        commandMap.put("changePasswordResult",new ChangePasswordResultCommand());
        commandMap.put("deleteUser", new DeleteUserCommand());
        commandMap.put("forgotPassword", new ForgotPasswordCommand());
        commandMap.put("forgotPasswordResult",new ForgotPasswordResultCommand());
        commandMap.put("home", new HomeCommand());
        commandMap.put("loadImage",new LoadImageCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("updateAccountStatus",new UpdateAccountStatusCommand());
        commandMap.put("updateUser", new UpdateUserCommand());
        commandMap.put("updateUserRole",new UpdateUserRoleCommand());
        commandMap.put("updateResultUser", new UpdateResultUserCommand());
        commandMap.put("updateDish", new UpdateDishCommand());
        commandMap.put("updateDishResult", new UpdateDishResultCommand());
        commandMap.put("uploadUserAvatar", new UploadUserAvatarCommand());
        commandMap.put("uploadUserAvatarResult", new UploadUserAvatarResultCommand());
        commandMap.put("uploadDishImage",new UploadDishImageCommand());
        commandMap.put("uploadDishImageResult",new UploadDishImageResultCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("registrationResult",new RegistrationResultCommand());
        commandMap.put("showUser", new ShowUserCommand());
        commandMap.put("showAllUsers", new ShowAllUsersCommand());
        commandMap.put("showDish",new ShowDishCommand());
        commandMap.put("showMenu",new ShowMenuCommand());
        commandMap.put("showShoppingCart",new ShowShoppingCartCommand());
        commandMap.put("showShoppingCartResult",new ShowShoppingCartResultCommand());
    }

    public Command getCommand(String action) {
        return commandMap.get(action);

    }
}
