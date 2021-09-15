package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.UserService;
import edu.epam.web.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ActivateAccountCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ActivateAccountCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        UserService service = new UserServiceImpl();
        Map<String, String> messages = new HashMap<String, String>();
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
            throw new CommandException(e);
        }
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        Router router=new Router(PagePath.ACTIVATE_ACCOUNT_PAGE);
        return router;
    }
}
