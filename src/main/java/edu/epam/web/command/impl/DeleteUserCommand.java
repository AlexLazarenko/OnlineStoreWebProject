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
import java.util.ArrayList;
import java.util.List;

public class DeleteUserCommand extends Command {
    private static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoService service = new UserDaoService();
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher usersPage = request.getRequestDispatcher("/jsp/usersAdmin.jsp");
        List<User> allUsers = new ArrayList<>();
        service.deleteUser(id);
        allUsers = service.findUsers();
        request.setAttribute("allUsers", allUsers);
        usersPage.forward(request, response);
    }
}

