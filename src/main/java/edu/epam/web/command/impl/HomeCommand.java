package edu.epam.web.command.impl;

import edu.epam.web.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
   /*     String name = request.getParameter("name");
        String age = request.getParameter("age");
        int result=Integer.parseInt(age);
        request.setAttribute("name", name);
        request.setAttribute("age", result);*/
        RequestDispatcher add = request.getRequestDispatcher("/jsp/main.jsp");
        add.forward(request, response);
    }
}