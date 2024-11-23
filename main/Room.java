package main;

/**
 * The {@code Room} class represents a room in the hotel system.
 * It contains details about the room, such as its ID, number, type, price, and availability.
 */
public class Room {

    /** The unique ID of the room. */
    private int roomID;

    /** The room number (e.g., "101", "202"). */
    private String roomNum;

    /** The type of the room (e.g., SINGLE, DOUBLE, SUITE). */
    private RoomType roomType;

    /** The price of the room per night. */
    private double price;

    /** The availability status of the room. */
    private boolean avail;

    /**
     * Enumeration representing the possible types of rooms.
     */
    public enum RoomType {
        SINGLE, DOUBLE, SUITE
    }

    /**
     * Constructs a new {@code Room} object with the specified details.
     *
     * @param roomID the unique ID of the room.
     * @param roomNum the room number.
     * @param roomType the type of the room.
     * @param price the price of the room per night.
     * @param avail the availability status of the room.
     */
    public Room(int roomID, String roomNum, RoomType roomType, double price, boolean avail) {
        this.roomID = roomID;
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.price = price;
        this.avail = avail;
    }

    // Getters

    /**
     * Returns the unique ID of the room.
     *
     * @return the room ID.
     */
    public int getRoomId() {
        return roomID;
    }

    /**
     * Returns the room number.
     *
     * @return the room number.
     */
    public String getRoomNum() {
        return roomNum;
    }

    /**
     * Returns the type of the room.
     *
     * @return the room type.
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Returns the price of the room per night.
     *
     * @return the room price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the availability status of the room.
     *
     * @return {@code true} if the room is available, otherwise {@code false}.
     */
    public boolean getAvail() {
        return avail;
    }

    // Setters

    /**
     * Sets the unique ID of the room.
     *
     * @param roomID the new room ID.
     */
    public void setRoomId(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Sets the room number.
     *
     * @param roomNum the new room number.
     */
    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * Sets the type of the room.
     *
     * @param roomType the new room type.
     */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /**
     * Sets the price of the room per night.
     *
     * @param price the new room price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the availability status of the room.
     *
     * @param avail the new availability status.
     */
    public void setAvail(boolean avail) {
        this.avail = avail;
    }

    /**
     * Returns whether the room is available.
     *
     * @return {@code true} if the room is available, otherwise {@code false}.
     */
    public boolean isAvail() {
        return avail;
    }

    /**
     * Returns a string representation of the room details.
     *
     * @return a string containing the room details.
     */
    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomID +
                ", roomNumber='" + roomNum + '\'' +
                ", roomType='" + roomType + '\'' +
                ", price=" + price +
                ", availability=" + avail +
                '}';
    }
}
