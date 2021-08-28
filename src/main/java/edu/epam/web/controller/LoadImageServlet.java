package edu.epam.web.controller;


import edu.epam.web.command.Command;
import edu.epam.web.command.CommandFactory;
import edu.epam.web.command.impl.UploadUserAvatarResultCommand;
import edu.epam.web.entity.User;
import edu.epam.web.exception.EmailException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;
import edu.epam.web.utility.FileUtil;
import edu.epam.web.utility.PropertyReaderUtil;
import edu.epam.web.validator.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class LoadImageServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoadImageServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getParameter("url");
        File file = new File(url);
        BufferedImage bi = ImageIO.read(file);
        OutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processReguest(request, response);
    }

    protected void processReguest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandString = request.getParameter("command");
        System.out.println(commandString);
        CommandFactory factory = CommandFactory.getInstance();
        Command command = factory.getCommand(commandString);
        try {
            command.execute(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ValidatorException e) {
            e.printStackTrace();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}




