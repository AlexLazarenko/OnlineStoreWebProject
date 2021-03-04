package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.exception.ValidatorException;

import edu.epam.web.service.UserDaoService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public class UploadImageCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        response.setContentType("text/html");
     //   UserDaoService service = new UserDaoService();
        byte[]avatar=uploadImage(request,response);
        int id = Integer.parseInt(request.getParameter("id"));
      //  service.updateAvatar(id,avatar);
        RequestDispatcher add = request.getRequestDispatcher("/jsp/uploadImage.jsp");
        add.forward(request, response);
    }

    private byte[] uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DiskFileItemFactory fileFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileFactory);
            List<FileItem> items = servletFileUpload.parseRequest((RequestContext) request);
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
