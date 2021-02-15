package edu.epam.web.utility;

import edu.epam.web.exception.PropertyReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger LOGGER = LogManager.getLogger(PropertiesLoader.class);

    private PropertiesLoader() {
    }

    /**
     * Read properties properties.
     *
     * @param path the path
     * @return the properties
     * @throws PropertyReaderException the property reader exception
     */
    public static Properties readProperties(String path) throws PropertyReaderException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            LOGGER.info("Properties were successfully read form '{}'", path);
            return properties;
        } catch (IOException e) {
            throw new PropertyReaderException("Impossible to read properties!");
        }
    }

}

