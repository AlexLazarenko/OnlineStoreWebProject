package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

public class LogoutCommand extends Command {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        HttpSession session = request.getSession();
        session.invalidate();
        String loginURI = request.getContextPath() + "/Home?action=login";
        response.sendRedirect(loginURI);
    }
}
