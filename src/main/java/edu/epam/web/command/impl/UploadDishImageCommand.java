package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.exception.ValidatorException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class UploadDishImageCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        response.setContentType("text/html");
        RequestDispatcher upload = request.getRequestDispatcher(PagePath.UPLOAD_DISH_IMAGE_PAGE);
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        request.setAttribute(RequestAttribute.ID, id);
        upload.forward(request, response);
    }
}
