package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.User;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.UserService;
import edu.epam.web.model.service.impl.UserServiceImpl;
import edu.epam.web.utility.FileUtil;
import edu.epam.web.utility.PropertyReaderUtil;
import edu.epam.web.validator.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


public class UploadUserAvatarResultCommand extends Command {
    private static final String PATH = PropertyReaderUtil.getPath();
    private static final Logger logger = LogManager.getLogger(UploadUserAvatarResultCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        UserService service = new UserServiceImpl();
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
            throw new CommandException(e);
        }

        if (isCorrect) {
            try {
                service.updateAvatar(user.getId(), user.getAvatar());
                request.getSession().setAttribute(SessionAttribute.USER, user);
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
            if (oldUserImage != null) {
                FileUtil.deleteImage(PATH + oldUserImage);
            }
        } else logger.error("some error save file, try again");

        Router router = new Router(PagePath.UPLOAD_USER_IMAGE_PAGE);
        return router;
    }
}



