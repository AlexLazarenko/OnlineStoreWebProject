package edu.epam.web.controller;




import edu.epam.web.entity.User;



import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/images/*")
@MultipartConfig(location = "C:/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class LoadImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processReguest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processReguest(request, response);
    }

    protected void processReguest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String imageName = request.getPathInfo().substring(1);
        byte[] bytes = user.getAvatar(); //todo byte image to jsp
        System.out.println(user.toString());
        System.out.println(bytes);
        System.out.println(imageName);
        response.setContentType(request.getServletContext().getMimeType(imageName));
      //  response.setContentType("image/jpeg");
        response.setContentLength(bytes.length);
        //  ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        //  BufferedImage bImage2 = ImageIO.read(bis);
        //  ImageIO.setCacheDirectory(new File("C:\\JAVA"));
        response.getOutputStream().write(bytes);
        //  ImageIO.write(bImage2, "jpg", outputStream);
        //   outputStream.close();
     //   outputStream.write(bytes);
    //    outputStream.flush();
     //   outputStream.close();
}


}
