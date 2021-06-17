package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.User;
import edu.epam.web.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Arrays;

@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class LoadImageCommand extends Command {
    private static final Logger logger = LogManager.getLogger(LoadImageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        User user = (User) request.getSession().getAttribute("user");
        //   String url = request.getParameter("url");
        byte[] bytes = user.getAvatar(); //todo byte image to jsp
        System.out.println(user.toString());
        System.out.println(bytes);
        //  response.setContentType(request.getServletContext().getMimeType(url));
        response.setContentType("image/jpeg");
        response.setContentLength(bytes.length);
        //  ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        //  BufferedImage bImage2 = ImageIO.read(bis);
        //  ImageIO.setCacheDirectory(new File("C:\\JAVA"));
        OutputStream outputStream = response.getOutputStream();
        //  ImageIO.write(bImage2, "jpg", outputStream);
        //   outputStream.close();
        outputStream.write(bytes);
    }
}
