package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.dao.UserDao;
import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.User;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;
import edu.epam.web.utility.EncryptPasswordUtil;
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

public class LoginCommand extends Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        UserDaoService service = new UserDaoService();
        String telephoneNumber = request.getParameter("telephone");
        String password = request.getParameter("password");
        Map<String, String> messages = new HashMap<String, String>();

        if (telephoneNumber != null && !telephoneNumber.isEmpty() && password != null && !password.isEmpty()) { //todo сделать отдельную команду для отрисовки формы?
            String storedPassword = EncryptPasswordUtil.encrypt(password);
            User user = service.findByTelephoneNumberPassword(telephoneNumber, storedPassword);
            String storedTelephoneNumber = service.findUserTelephoneNumber(telephoneNumber);
            if (storedTelephoneNumber == null) {
                messages.put("telephone", "Unknown telephone number, please try again");
            } else if (service.findUserTelephoneNumber(telephoneNumber) != null && user == null) {
                messages.put("password", "Wrong password, please try again");
            }
            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/Home");
                return;
            }
        }
       // request.setAttribute("telephone", telephoneNumber);
      //  request.setAttribute("password", password);
        request.setAttribute("messages", messages);
        RequestDispatcher login = request.getRequestDispatcher("/jsp/login.jsp");
        login.forward(request, response);
    }
}

