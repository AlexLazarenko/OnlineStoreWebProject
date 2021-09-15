package edu.epam.web.command;

import edu.epam.web.exception.CommandException;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ValidatorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public abstract class Command {

    public abstract Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException;
}