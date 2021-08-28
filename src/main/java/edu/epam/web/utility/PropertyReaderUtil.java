package edu.epam.web.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type property reader util.
 */
public class PropertyReaderUtil {

        /**
         * The constant logger.
         */
        private static final Logger logger = LogManager.getLogger(PropertyReaderUtil.class);
        private static final String PATH_PROPERTIES = "property/path.properties";
        private static final String PATH_PROPERTY = "path";
        private static final String PATH;

        private PropertyReaderUtil() {
        }

        static {
            ClassLoader classLoader = PropertyReaderUtil.class.getClassLoader();
            Properties properties = new Properties();
            try {
                InputStream inputStream = classLoader.getResourceAsStream(PATH_PROPERTIES);
                properties.load(inputStream);
                PATH = properties.getProperty(PATH_PROPERTY);
            } catch (IOException e) {
                logger.error(e);
                throw new RuntimeException(e);
            }
        }

        /**
         * Gets path.
         *
         * @return the string
         */
        public static String getPath() {
            return PATH;
        }
}
