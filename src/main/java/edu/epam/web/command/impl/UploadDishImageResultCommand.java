package edu.epam.web.command.impl;

import edu.epam.web.command.Command;
import edu.epam.web.command.PagePath;
import edu.epam.web.command.RequestAttribute;
import edu.epam.web.command.RequestParameter;
import edu.epam.web.entity.Dish;
import edu.epam.web.exception.ServiceException;
import edu.epam.web.exception.ValidatorException;
import edu.epam.web.service.DishDaoService;
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
import java.util.Optional;

public class UploadDishImageResultCommand extends Command {//todo correct?????!!!!
    private static final String PATH = PropertyReaderUtil.getPath();
    private static final Logger logger = LogManager.getLogger(UploadDishImageResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ValidatorException {
        response.setContentType("text/html");
        DishDaoService service = new DishDaoService();
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
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
            RequestDispatcher error = request.getRequestDispatcher(PagePath.ERROR_500);
            error.forward(request,response);
        }


        RequestDispatcher add = request.getRequestDispatcher(PagePath.UPLOAD_DISH_IMAGE_PAGE);
        add.forward(request, response);
    }
}
