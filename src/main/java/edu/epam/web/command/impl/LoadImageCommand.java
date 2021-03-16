package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.User;
import edu.epam.web.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class LoadImageCommand extends Command {
    private static final Logger logger = LogManager.getLogger(LoadImageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        User user = (User) request.getSession().getAttribute("user");
        byte[] bytes = user.getAvatar(); //todo byte image to jsp
        //System.out.println(bytes);
        response.setContentType("image/jpeg");
     //   ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
     //   BufferedImage bImage2 = ImageIO.read(bis);
     //   ImageIO.write(bImage2, "jpg", new File("output.jpg") );
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
    }
}
