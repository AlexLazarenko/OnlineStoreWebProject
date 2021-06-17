package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.entity.User;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
@MultipartConfig(location="C:/tmp", fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadUserAvatarResultCommand extends Command {
    private static final Logger logger = LogManager.getLogger(UploadUserAvatarResultCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        response.setContentType("text/html");
        UserDaoService service = new UserDaoService();
        byte[]avatar=uploadImage(request,response);
        User user=(User)request.getSession().getAttribute("user");
        service.updateAvatar(user.getId(),avatar);
        RequestDispatcher add = request.getRequestDispatcher("/jsp/uploadUserAvatar.jsp");
        add.forward(request, response);
    }

    private byte[] uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DiskFileItemFactory fileFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileFactory);
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for (FileItem item : items) {
                InputStream fileContent = item.getInputStream();
                byte[] array = IOUtils.toByteArray(fileContent);
                return array;
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }
        return null;
    }
}
