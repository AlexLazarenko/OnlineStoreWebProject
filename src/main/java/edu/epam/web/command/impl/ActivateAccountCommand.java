package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;
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

public class ActivateAccountCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ActivateAccountCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        UserDaoService service = new UserDaoService();
        Map<String, String> messages = new HashMap<String, String>();
        RequestDispatcher activate = request.getRequestDispatcher(PagePath.ACTIVATE_ACCOUNT_PAGE);
        String email = request.getParameter(RequestParameter.EMAIL);
        try{
            int result=service.activateAccount(email);
            if (result == 0) {
                logger.error("Account status update failed. Try again!");
                messages.put("message","Account status update failed. Try again!");
            } else {
                logger.info("Account status updated");
                messages.put("message","Account status updated");
            }
        }catch (ServiceException e){
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION,e.getMessage());
        }
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        activate.forward(request, response);
    }
}
