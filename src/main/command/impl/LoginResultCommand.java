package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.AccountStatus;
import edu.epam.web.model.entity.Dish;
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

public class LoginResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(LoginResultCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService service = new UserServiceImpl();
        String telephoneNumber = request.getParameter(RequestParameter.TELEPHONE);
        String password = request.getParameter(RequestParameter.PASSWORD);
        Map<String, String> messages = new HashMap<String, String>();

        String storedPassword = EncryptPasswordUtil.encrypt(password);
        try {
            Optional<User> optionalUser = service.findByTelephoneNumberPassword(telephoneNumber, storedPassword);
            String storedTelephoneNumber = service.findUserTelephoneNumber(telephoneNumber);
            if (storedTelephoneNumber == null) {
                messages.put("telephone", "Unknown telephone number, please try again");
            }
            if (optionalUser.isPresent()) {
                AccountStatus accountStatus = optionalUser.get().getAccountStatus();
                if (accountStatus.equals(AccountStatus.NEW)) {
                    messages.put("message", "Your account is not activated, please check your email for letter with activation link");
                } else if (accountStatus.equals(AccountStatus.BAN)) {
                    messages.put("message", "Your account is banned, please contact with administrator to solve this problem");
                } else {
                    request.getSession().setAttribute(SessionAttribute.USER, optionalUser.get());
                    Map<Dish, Integer> map = new HashMap<>();
                    request.getSession().setAttribute(SessionAttribute.MAP, map);
                    Router router=new Router(request.getContextPath() + PagePath.MAIN_SERVLET_URL, Router.Type.REDIRECT);
                    return router;
                }
            } else {
                messages.put("password", "Wrong password, please try again");
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        request.setAttribute(RequestAttribute.MESSAGES, messages);
        Router router = new Router(PagePath.LOGIN_PAGE);
        return router;
    }
}

