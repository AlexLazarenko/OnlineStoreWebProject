package edu.epam.web.connection;

import edu.epam.web.exception.PropertyReaderException;
import edu.epam.web.utility.PropertiesLoader;
import edu.epam.web.utility.PropertiesPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static volatile ConnectionPool instance;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenAwayConnections;
    private final static int POOL_SIZE = 32;

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    private ConnectionPool() {    //todo
    /*    String propertiesPath = PropertiesPath.DB_PROPERTIES;
        Properties properties;
        try {
            properties = PropertiesLoader.readProperties(propertiesPath);
        } catch (PropertyReaderException e) {
            throw new RuntimeException("Unable to read DB properties!", e);
        }
        String url = properties.getProperty(PropertyName.DB_URL);
        String user = properties.getProperty(PropertyName.DB_USER);
        String password = properties.getProperty(PropertyName.DB_PASSWORD);
        String driverName = properties.getProperty(PropertyName.DRIVER_NAME); */
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "74222222";
        String driverName = "org.postgresql.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to register DB driver!", e);
        }
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
                LOGGER.info("Connection created!");
            } catch (SQLException e) {
                LOGGER.error("Unable to create connection!", e);
            }
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            if (givenAwayConnections.remove(connection)) {
                freeConnections.offer((ProxyConnection) connection);
            }
        } else {
            LOGGER.error("Releasing connection is not proxy!");
        }
    }


    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOGGER.error("Unable to deregister driver!", e);
            }
        });
    }
}
