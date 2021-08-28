package edu.epam.web.utility;

import java.io.File;
import java.util.UUID;

public class FileUtil {
        /**
         * The extension separator.
         */
        private static final String EXTENSION_SEPARATOR = ".";

        private FileUtil() {
        }

        /**
         * Generate name.
         *
         * @param fileName the file name
         * @return the string
         */
        public static String generateName(String fileName) {
            UUID uuid = UUID.randomUUID();
            String name = uuid.toString();
            int index = fileName.lastIndexOf(EXTENSION_SEPARATOR);
            String extension = fileName.substring(index);
            return name + extension;
        }

        /**
         * Delete image.
         *
         * @param fileName the file name
         */
        public static void deleteImage(String fileName) {
            File file = new File(fileName);
            file.delete();
        }
}
