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
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(path)) {
            properties.load(inputStream);
            LOGGER.info("Properties were successfully read form '{}'", path);
            return properties;
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            throw new PropertyReaderException("Impossible to read properties!");
        }
        return properties;
    }

}

