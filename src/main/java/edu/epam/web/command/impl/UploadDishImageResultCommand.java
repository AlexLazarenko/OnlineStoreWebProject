package edu.epam.web.command.impl;

import edu.epam.web.command.*;
import edu.epam.web.exception.CommandException;
import edu.epam.web.model.entity.Dish;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.model.service.DishService;
import edu.epam.web.model.service.impl.DishServiceImpl;
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
import java.util.Optional;

public class UploadDishImageResultCommand extends Command {
    private static final String PATH = PropertyReaderUtil.getPath();
    private static final Logger logger = LogManager.getLogger(UploadDishImageResultCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        response.setContentType("text/html");
        DishService service = new DishServiceImpl();
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        boolean isCorrect = false;

        try {
            Optional<Dish> optionalDish = service.findDishById(id);
            if (optionalDish.isPresent()) {
                String oldDishImage = optionalDish.get().getDishImage();

                try {
                    for (Part part : request.getParts()) {
                        if (FileValidator.isValidFile(part)) {
                            String fileName = part.getSubmittedFileName();
                            String newFileName = FileUtil.generateName(fileName);
                            part.write(PATH + newFileName);
                            optionalDish.get().setDishImage(newFileName);
                            isCorrect = true;
                        }
                    }
                } catch (IOException | ServletException e) {
                    logger.error("some error save file" + e);
                    throw new CommandException(e);
                }

                if (isCorrect) {
                    service.updateDishImage(id, optionalDish.get().getDishImage());
                    if (oldDishImage != null) {
                        FileUtil.deleteImage(PATH + oldDishImage);
                    }
                } else logger.error("some error save file, try again");
            }

        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        Router router = new Router(PagePath.UPLOAD_DISH_IMAGE_PAGE);
        return router;
    }
}
