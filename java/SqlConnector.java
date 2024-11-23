package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code SqlConnector} class provides methods for managing the database connection.
 * It handles establishing and closing connections to the database using JDBC.
 */
public class SqlConnector {

    /** The URL of the database to connect to. */
    private static final String URL = "jdbc:mysql://localhost:3306/test_db";

    /** The username for the database connection. */
    private static final String USER = "admin";

    /** The password for the database connection. */
    private static final String PASSWORD = "password";

    /** The shared {@code Connection} instance. */
    private static Connection connection = null;

    /**
     * Returns a connection to the database.
     * If no connection exists or the existing connection is closed, a new connection is created.
     *
     * @return the {@code Connection} object for interacting with the database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                try {
                    // Establish the database connection
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    System.out.println("You are connected.");
                } catch (SQLException e) {
                    System.err.println("Error with username or password: " + e.getMessage());
                }
            } catch (ClassNotFoundException e) {
                System.err.println("JDBC Driver not found: " + e.getMessage());
            }
        }
        return connection;
    }

    /**
     * Closes the database connection if it is open.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection is closed.");
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
