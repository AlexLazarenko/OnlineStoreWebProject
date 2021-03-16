package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.dao.UserDao;
import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.User;
import edu.epam.web.service.UserDaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UpdateUserCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        RequestDispatcher update = request.getRequestDispatcher("/jsp/updateUser.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        UserDaoService service = new UserDaoService();
        user = service.findUserById(id);
        request.setAttribute("user", user);
        update.forward(request, response);
    }
}
