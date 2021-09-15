package edu.epam.web.controller;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.connection.ConnectionPool;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name="Home", urlPatterns = {"/Home","*.do"})
public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processReguest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processReguest(request, response);
    }

    protected void processReguest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionPool.getInstance();
        String action = request.getParameter(RequestParameter.ACTION);

        if (action == null) {
            action = CommandAction.HOME;
        }

        try {
            CommandFactory factory = CommandFactory.getInstance();
            Command command = factory.getCommand(action);
            Router router=command.execute(request, response);
            if (router.getType().equals(Router.Type.FORWARD)){
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPagePath());
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect(router.getPagePath());
            }
        } catch (ServletException | IOException   | CommandException e) {
            logger.error(e);
            //todo!!!!!!!!!!!!!!!!!!!!!!
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
            error.forward(request,response);
        }
    }
}

