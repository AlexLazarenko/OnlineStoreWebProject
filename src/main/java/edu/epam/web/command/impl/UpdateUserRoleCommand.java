package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.AccountStatus;
import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UpdateUserRoleCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
        UserDaoService service = new UserDaoService();
        List<User> allUsers = new ArrayList<>();
        RequestDispatcher usersPage = request.getRequestDispatcher("/jsp/usersAdmin.jsp");
        int id= Integer.parseInt(request.getParameter("id"));
        UserRole role= UserRole.valueOf(request.getParameter("role"));
        service.updateUserRole(id,role);
        allUsers = service.findUsers();
        request.setAttribute("allUsers", allUsers);
        usersPage.forward(request, response);
    }
}
