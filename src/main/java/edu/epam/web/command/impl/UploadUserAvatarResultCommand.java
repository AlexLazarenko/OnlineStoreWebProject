package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.SessionAttribute;
import edu.epam.web.entity.User;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.UserDaoService;
import edu.epam.web.utility.FileUtil;
import edu.epam.web.utility.PropertyReaderUtil;
import edu.epam.web.validator.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import java.text.ParseException;

public class UploadUserAvatarResultCommand extends Command {
    private static final String PATH = PropertyReaderUtil.getPath();
    private static final Logger logger = LogManager.getLogger(UploadUserAvatarResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        UserDaoService service = new UserDaoService();
        boolean isCorrect = false;
        String oldUserImage = user.getAvatar();

        try {
            for (Part part : request.getParts()) {
                if (FileValidator.isValidFile(part)) {
                    String fileName = part.getSubmittedFileName();
                    String newFileName = FileUtil.generateName(fileName);
                    part.write(PATH + newFileName);
                    user.setAvatar(newFileName);
                    isCorrect = true;
                }
            }
        } catch (IOException | ServletException e) {
            logger.error("some error save file" + e);
        }

        if (isCorrect) {
            try {
                service.updateAvatar(user.getId(), user.getAvatar());
                request.getSession().setAttribute(SessionAttribute.USER, user);
            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
                RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
                error.forward(request,response);
            }
            if (oldUserImage != null) {
                FileUtil.deleteImage(PATH + oldUserImage);
            }
        } else logger.error("some error save file, try again");

        RequestDispatcher add = request.getRequestDispatcher(PagePath.UPLOAD_USER_IMAGE_PAGE);
        add.forward(request, response);
    }
}



