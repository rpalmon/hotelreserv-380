package main;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ReservationData} class provides CRUD operations for managing reservations
 * in the database. It includes methods to create, update, delete, and retrieve reservation records.
 */
public class ReservationData {

    /**
     * Adds a new reservation to the database.
     *
     * @param res the {@code Reservation} object containing the details of the reservation to be added.
     */
    public void addRes(Reservation res) {
        String qry = "INSERT INTO Reservation (guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setInt(1, res.getGuestId());
            prep.setInt(2, res.getRoomId());
            prep.setDate(3, java.sql.Date.valueOf(res.getCheckInDate()));
            prep.setDate(4, java.sql.Date.valueOf(res.getCheckOutDate()));
            prep.setDouble(5, res.getTotalCost());
            prep.setString(6, res.getStatus().name());
            prep.setBoolean(7, res.getPayment());

            if (prep.executeUpdate() > 0) {
                System.out.println("Reservation has been created.");
            } else {
                System.out.println("Failed to create reservation.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding reservation: " + e.getMessage());
        }
    }

    /**
     * Deletes a reservation from the database by its ID.
     *
     * @param resID the ID of the reservation to delete.
     */
    public void delRes(int resID) {
        String qry = "DELETE FROM Reservation WHERE resID = ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setInt(1, resID);
            if (prep.executeUpdate() > 0) {
                System.out.println("Reservation has been deleted.");
            } else {
                System.out.println("Failed to delete reservation.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting reservation: " + e.getMessage());
        }
    }

    /**
     * Updates an existing reservation in the database.
     *
     * @param res the {@code Reservation} object containing the updated reservation details.
     */
    public void updateRes(Reservation res) {
        String qry = "UPDATE Reservation SET guestID=?, roomID=?, checkInDate=?, checkOutDate=?, totalCost=?, status=?, payment=? WHERE resID=?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setInt(1, res.getGuestId());
            prep.setInt(2, res.getRoomId());
            prep.setDate(3, java.sql.Date.valueOf(res.getCheckInDate()));
            prep.setDate(4, java.sql.Date.valueOf(res.getCheckOutDate()));
            prep.setDouble(5, res.getTotalCost());
            prep.setString(6, res.getStatus().name());
            prep.setBoolean(7, res.getPayment());
            prep.setInt(8, res.getResId());

            if (prep.executeUpdate() > 0) {
                System.out.println("Reservation has been updated.");
            } else {
                System.out.println("Failed to update reservation.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating reservation: " + e.getMessage());
        }
    }

    /**
     * Retrieves a reservation from the database by its ID.
     *
     * @param resID the ID of the reservation to retrieve.
     * @return the {@code Reservation} object, or {@code null} if not found.
     */
    public Reservation getRes(int resID) {
        Reservation reservation = null;
        String qry = "SELECT * FROM Reservation WHERE resID = ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setInt(1, resID);
            ResultSet result = prep.executeQuery();
            if (result.next()) {
                reservation = new Reservation(
                        result.getInt("resID"),
                        result.getInt("guestID"),
                        result.getInt("roomID"),
                        result.getDate("checkInDate").toLocalDate(),
                        result.getDate("checkOutDate").toLocalDate(),
                        result.getDouble("totalCost"),
                        Reservation.Status.valueOf(result.getString("status")),
                        result.getBoolean("payment")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving reservation: " + e.getMessage());
        }
        return reservation;
    }

    /**
     * Retrieves all reservations from the database.
     *
     * @return a {@code List} of {@code Reservation} objects containing all reservation records.
     */
    public List<Reservation> listAllRes() {
        List<Reservation> res = new ArrayList<>();
        String qry = "SELECT * FROM Reservation";

        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {

            ResultSet result = prep.executeQuery();

            while (result.next()) {
                int resID = result.getInt("resID");
                int guestID = result.getInt("guestID");
                int roomID = result.getInt("roomID");
                LocalDate checkInDate = result.getDate("checkInDate").toLocalDate();
                LocalDate checkOutDate = result.getDate("checkOutDate").toLocalDate();
                double totalCost = result.getDouble("totalCost");
                Reservation.Status status = Reservation.Status.valueOf(result.getString("status"));
                boolean payment = result.getBoolean("payment");

                res.add(new Reservation(resID, guestID, roomID, checkInDate, checkOutDate, totalCost, status, payment));
            }

        } catch (SQLException e) {
            System.err.println("Error listing all reservations: " + e.getMessage());
        }
        return res;
    }
}
