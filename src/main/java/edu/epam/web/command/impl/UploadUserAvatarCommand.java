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

public class UploadUserAvatarCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UploadUserAvatarCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        response.setContentType("text/html");
        RequestDispatcher upload = request.getRequestDispatcher("/jsp/uploadUserAvatar.jsp");
        upload.forward(request, response);
    }
}
