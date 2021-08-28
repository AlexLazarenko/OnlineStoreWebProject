package edu.epam.web.validator;

import javax.servlet.http.Part;

public class FileValidator {
    private FileValidator() {
    }

    /**
     * Is valid file boolean.
     *
     * @param part the part
     * @return the boolean
     */
    public static boolean isValidFile(Part part) {
        boolean isCorrect = true;
        if (part.getSubmittedFileName() == null || part.getSubmittedFileName().isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
