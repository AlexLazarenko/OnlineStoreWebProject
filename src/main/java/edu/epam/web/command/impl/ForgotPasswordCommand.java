package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * The type Forgot password command.
 */
public class ForgotPasswordCommand extends Command {

    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ForgotPasswordCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute(RequestAttribute.MESSAGES, messages);
        Router router = new Router(PagePath.FORGOT_PASSWORD_PAGE);
        return router;
    }
}
