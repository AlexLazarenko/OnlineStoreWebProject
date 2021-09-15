package edu.epam.web.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDao {
    /**
     * The constant logger.
     */
    Logger logger = LogManager.getLogger(BaseDao.class);

    /**
     * Close.
     *
     * @param statement the statement
     */
    default void close(Statement statement){
        try {
            if(statement!=null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    /**
     * Close.
     *
     * @param connection the connection
     */
    default void close(Connection connection){
        try {
            if(connection!=null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    /**
     * RollBack.
     *
     * @param connection the connection
     */
    default void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e ){
            logger.error("rollback error");
        }
    }
}
