package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.Guest;

/**
 * The {@code GuestData} class provides CRUD operations for managing guest data in the database.
 */
public class GuestData {

    /**
     * Adds a new guest to the database.
     *
     * @param guest the {@code Guest} object containing the details of the guest to be added.
     */
    public void addGuest(Guest guest) {
        String qry = "INSERT INTO Guest (name, email, phone, address) VALUES (?, ?, ?, ?)";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setString(1, guest.getName());
            prep.setString(2, guest.getEmail());
            prep.setString(3, guest.getPhone());
            prep.setString(4, guest.getAddress());
            if (prep.executeUpdate() > 0) {
                System.out.println("Guest has been added.");
            } else {
                System.out.println("Failed to add guest.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding guest: " + e.getMessage());
        }
    }

    /**
     * Retrieves a guest by their unique ID.
     *
     * @param guestID the ID of the guest to retrieve.
     * @return the {@code Guest} object containing the guest's details, or {@code null} if not found.
     */
    public Guest getGuestID(int guestID) {
        Guest guest = null;
        String qry = "SELECT * FROM Guest WHERE GuestID = ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setInt(1, guestID);
            ResultSet result = prep.executeQuery();
            if (result.next()) {
                guest = new Guest(
                    result.getInt("guestID"),
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("phone"),
                    result.getString("address")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving guest by ID: " + e.getMessage());
        }
        return guest;
    }

    /**
     * Updates the details of an existing guest in the database.
     *
     * @param guest the {@code Guest} object containing the updated guest details.
     */
    public void updateGuest(Guest guest) {
        String qry = "UPDATE Guest SET name = ?, email = ?, phone = ?, address = ? WHERE guestID = ?";
        try (Connection connection = SqlConnector.getConnection();
             PreparedStatement prep = connection.prepareStatement(qry)) {
            prep.setString(1, guest.getName());
            prep.setString(2, guest.getEmail());
            prep.setString(3, guest.getPhone());
            prep.setString(4, guest.getAddress());
            prep.setInt(5, guest.getGuestId());
            if (prep.executeUpdate() > 0) {
                System.out.println("Guest has been updated.");
            } else {
                System.out.println("Failed to update guest.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating guest: " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of all guests in the database.
     *
     * @return a {@code List} of {@code Guest} objects containing all guest details.
     */
    public List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();
        String qry = "SELECT * FROM Guest";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry);
             ResultSet result = prep.executeQuery()) {
            while (result.next()) {
                int id = result.getInt("GuestId");
                String name = result.getString("name");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String address = result.getString("address");
                guests.add(new Guest(id, name, email, phone, address));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving guest list: " + e.getMessage());
        }
        return guests;
    }

    /**
     * Deletes a guest from the database based on their ID.
     *
     * @param guestID the ID of the guest to delete.
     */
    public void delGuest(int guestID) {
        String qry = "DELETE FROM Guest WHERE GuestID = ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setInt(1, guestID);
            if (prep.executeUpdate() > 0) {
                System.out.println("Guest has been deleted.");
            } else {
                System.out.println("Failed to delete guest.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting guest: " + e.getMessage());
        }
    }

    /**
     * Searches for guests by their name (partial or full).
     *
     * @param gName the name (or partial name) of the guest to search for.
     * @return a {@code List} of {@code Guest} objects matching the search criteria.
     */
    public List<Guest> guestNameSearch(String gName) {
        List<Guest> guests = new ArrayList<>();
        String qry = "SELECT * FROM Guest WHERE name LIKE ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setString(1, "%" + gName + "%");
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                int id = result.getInt("GuestId");
                String name = result.getString("name");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String address = result.getString("address");
                guests.add(new Guest(id, name, email, phone, address));
            }
        } catch (SQLException e) {
            System.err.println("Error searching guests by name: " + e.getMessage());
        }
        return guests;
    }
}
