package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ValidatorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class ShowShoppingCartResultCommand extends Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, ValidatorException, EmailException {
       // fff
    }
}
