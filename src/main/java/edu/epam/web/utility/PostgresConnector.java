package edu.epam.web.utility;

import edu.epam.web.connection.PropertyName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*public class PostgresConnector { //todo

        public static Connection getConnection() {

            System.out.println("Testing connection to PostgreSQL JDBC");

            try {
                Class.forName(PropertyName.DRIVER_NAME);
            } catch (ClassNotFoundException e) {
                System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
                e.printStackTrace();
                return null;
            }

            System.out.println("PostgreSQL JDBC Driver successfully connected");
            Connection connection = null;

            try {
                connection = DriverManager
                        .getConnection(PropertyName.DB_URL, PropertyName.DB_USER, PropertyName.DB_PASSWORD);

            } catch (SQLException e) {
                System.out.println("Connection Failed");
                e.printStackTrace();
                return null;
            }

            if (connection != null) {
                System.out.println("You successfully connected to database now");
            } else {
                System.out.println("Failed to make connection to database");
            }return connection;
        }
    }*/

