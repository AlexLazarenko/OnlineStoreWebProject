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
        commandMap.put("showUser", new ShowUserCommand());
        commandMap.put("showAllUsers", new ShowAllUsersCommand());
        commandMap.put("updateUser", new UpdateUserCommand());
        commandMap.put("updateResultUser", new UpdateResultUserCommand());
        commandMap.put("deleteUser", new DeleteUserCommand());
        commandMap.put("home", new HomeCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("uploadImage", new UploadImageCommand());
        commandMap.put("uploadImageResult", new UploadImageResultCommand());
        commandMap.put("registrationResult",new RegistrationResultCommand());
        commandMap.put("loadImage",new LoadImageCommand());
        commandMap.put("changePassword",new ChangePasswordCommand());
        commandMap.put("changePasswordResult",new ChangePasswordResultCommand());
    }

    public Command getCommand(String action) {
        return commandMap.get(action);

    }
}
