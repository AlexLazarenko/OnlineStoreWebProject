package edu.epam.web.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static edu.epam.web.utility.PropertiesPath.DB_PROPERTIES;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static volatile ConnectionPool instance;
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenAwayConnections;
    private final static int POOL_SIZE = 32;
    private static Properties properties;
    private static String url;
    private static String user;
    private static String password;
    private static String driverName;

    static {
        properties = new Properties();
        try {
            ClassLoader classLoader = ConnectionPool.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream(DB_PROPERTIES);
            properties.load(resourceAsStream);
            url = properties.getProperty(PropertyName.DB_URL);
            user = properties.getProperty(PropertyName.DB_USER);
            password = properties.getProperty(PropertyName.DB_PASSWORD);
            driverName = properties.getProperty(PropertyName.DRIVER_NAME);
        } catch (IOException e) {
            logger.error("Error uploading a file", e);
            throw new RuntimeException(e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!isInitialized.get()) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                isInitialized.set(true);
            }
            lock.unlock();
        }
        return instance;
    }

    private ConnectionPool() {
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
                logger.info("Connection created!");
            } catch (SQLException e) {
                logger.error("Unable to create connection!", e);
            }
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            if (givenAwayConnections.remove(connection)) {
                freeConnections.offer((ProxyConnection) connection);
            }
        } else {
            logger.error("Releasing connection is not proxy!");
        }
    }


    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Unable to deregister driver!", e);
            }
        });
    }
}
