package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.dao.UserDao;
import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.User;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        UserDaoService service = new UserDaoService();
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        Map<String, String> messages = new HashMap<String, String>();

        if (id == null || id.isEmpty()) {
            messages.put("id", "Please enter login");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }

        if (messages.isEmpty()) {
            User user = service.readUserByIdPassword(id, password);

            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/Home");
                return;
            } else {
                messages.put("id", "Unknown id, please try again");
            }
        }

        request.setAttribute("messages", messages);
        RequestDispatcher login=request.getRequestDispatcher( "/jsp/login.jsp");
        login.forward(request, response);
    }
}

