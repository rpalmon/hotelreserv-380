package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code RoomData} class provides CRUD operations for managing room data in the database.
 * It includes methods to create, update, delete, and retrieve room records.
 */
public class RoomData {

    /**
     * Adds a new room to the database.
     *
     * @param room the {@code Room} object containing the room details to add.
     */
    public void addRoom(Room room) {
        String qry = "INSERT INTO Room (roomNum, roomType, price, avail) VALUES (?, ?, ?, ?)";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setString(1, room.getRoomNum());
            prep.setString(2, room.getRoomType().name());
            prep.setDouble(3, room.getPrice());
            prep.setBoolean(4, room.getAvail());
            if (prep.executeUpdate() > 0) {
                System.out.println("Room has been added.");
            } else {
                System.out.println("Failed to add room.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding room: " + e.getMessage());
        }
    }

    /**
     * Deletes a room from the database by its ID.
     *
     * @param roomID the ID of the room to delete.
     */
    public void delRoom(int roomID) {
        String qry = "DELETE FROM Room WHERE roomID = ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setInt(1, roomID);
            if (prep.executeUpdate() > 0) {
                System.out.println("Room has been deleted.");
            } else {
                System.out.println("Failed to delete room.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting room: " + e.getMessage());
        }
    }

    /**
     * Updates an existing room in the database.
     *
     * @param room the {@code Room} object containing the updated room details.
     */
    public void updateRoom(Room room) {
        String qry = "UPDATE Room SET roomNum=?, roomType=?, price=?, avail=? WHERE roomID=?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setString(1, room.getRoomNum());
            prep.setString(2, room.getRoomType().name());
            prep.setDouble(3, room.getPrice());
            prep.setBoolean(4, room.getAvail());
            prep.setInt(5, room.getRoomId());
            if (prep.executeUpdate() > 0) {
                System.out.println("Room has been updated.");
            } else {
                System.out.println("Failed to update room.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating room: " + e.getMessage());
        }
    }

    /**
     * Retrieves a room by its ID.
     *
     * @param roomID the ID of the room to retrieve.
     * @return the {@code Room} object, or {@code null} if not found.
     */
    public Room getRoomID(int roomID) {
        Room room = null;
        String qry = "SELECT * FROM Room WHERE roomID = ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setInt(1, roomID);
            ResultSet result = prep.executeQuery();
            if (result.next()) {
                room = new Room(
                    result.getInt("roomID"),
                    result.getString("roomNum"),
                    Room.RoomType.valueOf(result.getString("roomType").toUpperCase()),
                    result.getDouble("price"),
                    result.getBoolean("avail")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving room: " + e.getMessage());
        }
        return room;
    }

    /**
     * Searches for rooms by their room number.
     *
     * @param roomNum the room number to search for.
     * @return a {@code List} of {@code Room} objects matching the search criteria.
     */
    public List<Room> roomNumSearch(String roomNum) {
        List<Room> rooms = new ArrayList<>();
        String qry = "SELECT * FROM Room WHERE roomNum LIKE ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setString(1, "%" + roomNum + "%");
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                rooms.add(new Room(
                    result.getInt("roomID"),
                    result.getString("roomNum"),
                    Room.RoomType.valueOf(result.getString("roomType").toUpperCase()),
                    result.getDouble("price"),
                    result.getBoolean("avail")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error searching rooms: " + e.getMessage());
        }
        return rooms;
    }

    /**
     * Retrieves a list of all rooms in the database.
     *
     * @return a {@code List} of {@code Room} objects containing all room records.
     */
    public List<Room> listRooms() {
        List<Room> rooms = new ArrayList<>();
        String qry = "SELECT * FROM Room";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry);
             ResultSet result = prep.executeQuery()) {
            while (result.next()) {
                rooms.add(new Room(
                    result.getInt("roomID"),
                    result.getString("roomNum"),
                    Room.RoomType.valueOf(result.getString("roomType").toUpperCase()),
                    result.getDouble("price"),
                    result.getBoolean("avail")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error listing rooms: " + e.getMessage());
        }
        return rooms;
    }

    /**
     * Retrieves a list of available rooms in the database.
     *
     * @return a {@code List} of {@code Room} objects containing available rooms.
     */
    public List<Room> listAvailRoom() {
        List<Room> rooms = new ArrayList<>();
        String qry = "SELECT * FROM Room WHERE avail = TRUE";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry);
             ResultSet result = prep.executeQuery()) {
            while (result.next()) {
                rooms.add(new Room(
                    result.getInt("roomID"),
                    result.getString("roomNum"),
                    Room.RoomType.valueOf(result.getString("roomType").toUpperCase()),
                    result.getDouble("price"),
                    result.getBoolean("avail")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error listing available rooms: " + e.getMessage());
        }
        return rooms;
    }

    /**
     * Updates the price of a room by its ID.
     *
     * @param id the ID of the room.
     * @param price the new price to set.
     */
    public void updatePrice(int id, double price) {
        String qry = "UPDATE Room SET price = ? WHERE roomID = ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(qry)) {
            prep.setDouble(1, price);
            prep.setInt(2, id);
            if (prep.executeUpdate() > 0) {
                System.out.println("Price has been updated to " + price + ".");
            } else {
                System.out.println("Failed to change price.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating price: " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of available rooms by type.
     *
     * @param roomType the type of rooms to search for.
     * @return a {@code List} of {@code Room} objects of the specified type.
     */
    public List<Room> listAvailableRoomsByType(String roomType) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Room WHERE avail = TRUE AND roomType = ?";
        try (Connection connect = SqlConnector.getConnection();
             PreparedStatement prep = connect.prepareStatement(sql)) {
            prep.setString(1, roomType);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                rooms.add(new Room(
                    result.getInt("roomID"),
                    result.getString("roomNum"),
                    Room.RoomType.valueOf(result.getString("roomType").toUpperCase()),
                    result.getDouble("price"),
                    result.getBoolean("avail")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error listing available rooms by type: " + e.getMessage());
        }
        return rooms;
    }
}
