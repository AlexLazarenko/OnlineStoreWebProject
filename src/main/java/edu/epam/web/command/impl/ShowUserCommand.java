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

public class ShowUserCommand extends Command {
    private static final Logger logger = LogManager.getLogger(ShowUserCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    User user = null;
        RequestDispatcher showUser = request.getRequestDispatcher("/jsp/userPage.jsp");
  //      request.getSession().getAttribute("user");
   //     int id = Integer.parseInt(request.getParameter("id"));
    //    UserDaoService service = new UserDaoService();
    //    user = service.findUserById(id);
    //    request.setAttribute("user", user);
        showUser.forward(request, response);
    }
}

