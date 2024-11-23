package main;

import java.time.LocalDate;

/**
 * The {@code Reservation} class represents a hotel reservation.
 * It contains details such as the reservation ID, guest ID, room ID, 
 * check-in and check-out dates, total cost, status, and payment status.
 */
public class Reservation {

    /** The unique ID of the reservation. */
    private int resID;

    /** The unique ID of the guest associated with the reservation. */
    private int guestID;

    /** The unique ID of the room associated with the reservation. */
    private int roomID;

    /** The check-in date of the reservation. */
    private LocalDate checkInDate;

    /** The check-out date of the reservation. */
    private LocalDate checkOutDate;

    /** The total cost of the reservation. */
    private double totalCost;

    /** The current status of the reservation. */
    private Status status;

    /** The payment status of the reservation. */
    private boolean payment;

    /**
     * Enumeration representing the possible statuses of a reservation.
     */
    public enum Status {
        BOOKED, CHECKEDIN, CHECKEDOUT, CANCELLED
    }

    /**
     * Constructs a new {@code Reservation} object with the specified details.
     *
     * @param resID the unique ID of the reservation.
     * @param guestID the unique ID of the guest associated with the reservation.
     * @param roomID the unique ID of the room associated with the reservation.
     * @param checkInDate the check-in date of the reservation.
     * @param checkOutDate the check-out date of the reservation.
     * @param totalCost the total cost of the reservation.
     * @param status the current status of the reservation.
     * @param payment the payment status of the reservation.
     */
    public Reservation(
        int resID, int guestID, int roomID, 
        LocalDate checkInDate, LocalDate checkOutDate, 
        double totalCost, Status status, boolean payment) {
        this.resID = resID;
        this.guestID = guestID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
        this.status = status;
        this.payment = payment;
    }

    // Getters

    /**
     * Returns the reservation ID.
     *
     * @return the reservation ID.
     */
    public int getResId() {
        return resID;
    }

    /**
     * Returns the guest ID associated with the reservation.
     *
     * @return the guest ID.
     */
    public int getGuestId() {
        return guestID;
    }

    /**
     * Returns the room ID associated with the reservation.
     *
     * @return the room ID.
     */
    public int getRoomId() {
        return roomID;
    }

    /**
     * Returns the check-in date of the reservation.
     *
     * @return the check-in date.
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     * Returns the check-out date of the reservation.
     *
     * @return the check-out date.
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Returns the total cost of the reservation.
     *
     * @return the total cost.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the current status of the reservation.
     *
     * @return the status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns the payment status of the reservation.
     *
     * @return {@code true} if payment is complete, otherwise {@code false}.
     */
    public boolean getPayment() {
        return payment;
    }

    /**
     * Returns whether the reservation has been paid for.
     *
     * @return {@code true} if the reservation is paid, otherwise {@code false}.
     */
    public boolean isPaid() {
        return payment;
    }

    // Setters

    /**
     * Sets the reservation ID.
     *
     * @param resID the reservation ID.
     */
    public void setResId(int resID) {
        this.resID = resID;
    }

    /**
     * Sets the guest ID for the reservation.
     *
     * @param guestID the guest ID.
     */
    public void setGuestId(int guestID) {
        this.guestID = guestID;
    }

    /**
     * Sets the room ID for the reservation.
     *
     * @param roomID the room ID.
     */
    public void setRoomId(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Sets the check-in date for the reservation.
     *
     * @param checkInDate the check-in date.
     */
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Sets the check-out date for the reservation.
     *
     * @param checkOutDate the check-out date.
     */
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * Sets the total cost of the reservation.
     *
     * @param totalCost the total cost.
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Sets the status of the reservation.
     *
     * @param status the new status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Sets the payment status of the reservation.
     *
     * @param payment the new payment status.
     */
    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    /**
     * Returns a string representation of the reservation.
     *
     * @return a string containing the reservation details.
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + resID +
                ", guestId=" + guestID +
                ", roomId=" + roomID +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalCost=" + totalCost +
                ", status='" + status + '\'' +
                '}';
    }
}
