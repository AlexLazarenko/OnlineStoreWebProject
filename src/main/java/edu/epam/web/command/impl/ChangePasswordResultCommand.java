package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserGender;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.factory.UserFactory;
import edu.epam.web.service.DateFormatService;
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

public class ChangePasswordResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        UserDaoService daoService = new UserDaoService();
        RequestDispatcher reg = request.getRequestDispatcher("/jsp/password.jsp");
        Map<String, String> messages = new HashMap<String, String>();
        User user = (User) request.getSession().getAttribute("user");
        String storedPassword = daoService.findPasswordById(user.getId());
        String password = EncryptPasswordUtil.encrypt(request.getParameter("password"));
        String newPassword = EncryptPasswordUtil.encrypt(request.getParameter("new_password"));
        String verifyPassword = EncryptPasswordUtil.encrypt(request.getParameter("password_two"));
        if (!EncryptPasswordUtil.checkPassword(storedPassword, password)) {
            logger.warn("Checking password fail");
            messages.put("fail", "It is not your password!");
        }
            if (EncryptPasswordUtil.checkPassword(password, newPassword)) {
                logger.warn("Checking password fail. You input same passwords.");
                messages.put("new_password", "Checking fail. You input same passwords.");
                messages.put("password", "Checking fail. You input same passwords.");
            }
                if (!EncryptPasswordUtil.checkPassword(newPassword, verifyPassword)) {
                    logger.warn("Verifying password fail. Input same passwords, please");
                    messages.put("new_password", "Verifying fail. Input same passwords, please");
                }
                if(messages.isEmpty()){
                    int flag = daoService.changePassword(user.getEmail(), newPassword);
                    if (flag == 0) {
                        messages.put("message", "Change password failed, please try again");
                    } else {
                        messages.put("message", "Change password successful");
                    }
                }
        request.setAttribute("messages", messages);//todo завершить сессию редиректнуть на логин?
        reg.forward(request, response);

    }
}
