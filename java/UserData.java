package main;

import java.sql.*;

/**
 * The {@code UserData} class provides methods for managing user data in the database.
 * It includes functionality to validate user credentials.
 */
public class UserData {

    /**
     * Validates a user's credentials by checking the database.
     *
     * @param username the username provided by the user.
     * @param pass the password provided by the user.
     * @return {@code true} if the credentials are valid, otherwise {@code false}.
     */
    public boolean validUser(String username, String pass) {
        String qry = "SELECT * FROM User WHERE userName = ? AND userPass = ?";
        
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
             
            // Set the username and password parameters
            prep.setString(1, username);
            prep.setString(2, pass);
            
            // Execute the query and check if a result exists
            ResultSet result = prep.executeQuery();

            return result.next(); // If a result is returned, the credentials are valid.
            
        } catch (SQLException e) {
            System.err.println("An error occurred while validating user: " + e.getMessage());
            return false;
        }
    }
}
