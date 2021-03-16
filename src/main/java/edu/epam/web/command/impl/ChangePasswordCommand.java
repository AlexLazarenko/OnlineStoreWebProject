package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.exception.ValidatorException;
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

public class ChangePasswordCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        RequestDispatcher reg = request.getRequestDispatcher("/jsp/password.jsp");
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        reg.forward(request, response);
    }
}
