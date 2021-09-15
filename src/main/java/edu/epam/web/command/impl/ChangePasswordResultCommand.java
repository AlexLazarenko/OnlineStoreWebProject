package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.User;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.UserService;
import edu.epam.web.model.service.impl.UserServiceImpl;
import edu.epam.web.utility.EncryptPasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangePasswordResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordResultCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService daoService = new UserServiceImpl();
        Map<String, String> messages = new HashMap<String, String>();
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        String password = EncryptPasswordUtil.encrypt(request.getParameter(RequestParameter.PASSWORD));
        String newPassword = EncryptPasswordUtil.encrypt(request.getParameter(RequestParameter.NEW_PASSWORD));
        String verifyPassword = EncryptPasswordUtil.encrypt(request.getParameter(RequestParameter.VERIFY_PASSWORD));
        try {
            String storedPassword = daoService.findPasswordById(user.getId());
            messages = EncryptPasswordUtil.checkPasswords(storedPassword, password, newPassword, verifyPassword);
            if (messages.isEmpty()) {
                int flag = daoService.changePassword(user.getEmail(), newPassword);
                if (flag == 0) {
                    messages.put("message", "Change password failed, please try again");
                } else {
                    messages.put("message", "Change password successful");
                    Optional<User> optionalUser = daoService.findUserById(user.getId());
                    optionalUser.ifPresent(value -> request.getSession().setAttribute(SessionAttribute.USER, value));
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        Router router = new Router(PagePath.CHANGE_PASSWORD_PAGE);
        return router;
    }
}
