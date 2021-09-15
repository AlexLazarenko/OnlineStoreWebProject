package edu.epam.web.command.impl;

import edu.epam.web.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadDishImageCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        request.setAttribute(RequestAttribute.ID, id);
        Router router = new Router(PagePath.UPLOAD_DISH_IMAGE_PAGE);
        return router;
    }
}
