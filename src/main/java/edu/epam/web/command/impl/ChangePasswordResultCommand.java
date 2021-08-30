package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.entity.User;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;
import edu.epam.web.utility.EncryptPasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangePasswordResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        UserDaoService daoService = new UserDaoService();
        Map<String, String> messages = new HashMap<String, String>();
        RequestDispatcher reg = request.getRequestDispatcher(PagePath.CHANGE_PASSWORD_PAGE);
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
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
            error.forward(request,response);
        }
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        reg.forward(request, response);
    }
}
