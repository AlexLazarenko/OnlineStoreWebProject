package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.entity.User;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;
import edu.epam.web.utility.EncryptPasswordUtil;
import edu.epam.web.utility.MailSenderUtil;
import edu.epam.web.utility.RandomStringGenerator;
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

public class ForgotPasswordResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ForgotPasswordResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        UserDaoService daoService = new UserDaoService();
        RequestDispatcher reg = request.getRequestDispatcher(PagePath.FORGOT_PASSWORD_PAGE);
        Map<String, String> messages = new HashMap<String, String>();
        String email = request.getParameter(RequestParameter.EMAIL);
        try{
            if (daoService.findUserEmail(email) == null) {
                logger.warn("This email not exists");
                messages.put("email", "This email is not exists");
            } else {
                String newPassword = RandomStringGenerator.generate();
                MailSenderUtil.sendNewPassword(email, newPassword);
                String encryptPassword = EncryptPasswordUtil.encrypt(newPassword);
                int flag = daoService.changePassword(email, encryptPassword);
                if (flag == 0) {
                    messages.put("message", "Change password failed, please try again");
                } else {
                    messages.put("message", "Change password successful");
                    Optional<User> optionalUser = daoService.findUserByEmail(email);
                    optionalUser.ifPresent(value -> request.getSession().setAttribute(SessionAttribute.USER, value));
                }
            }
        }catch (ServiceException e){
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION,e.getMessage());
            RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
            error.forward(request,response);
        }

        request.setAttribute(RequestAttribute.MESSAGES, messages);
        reg.forward(request, response);
    }
}

