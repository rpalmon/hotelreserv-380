package main;

/**
 * The {@code Guest} class represents a guest in the hotel reservation system.
 * It contains details such as the guest's ID, name, email, phone, and address.
 */
public class Guest {
    
    /** The unique ID of the guest. */
    private int guestId;
    
    /** The name of the guest. */
    private String name;
    
    /** The email address of the guest. */
    private String email;
    
    /** The phone number of the guest. */
    private String phone;
    
    /** The address of the guest. */
    private String address;

    /**
     * Constructs a new {@code Guest} object with the specified details.
     *
     * @param guestId the unique ID of the guest.
     * @param name the name of the guest.
     * @param email the email address of the guest.
     * @param phone the phone number of the guest.
     * @param address the address of the guest.
     */
    public Guest(int guestId, String name, String email, String phone, String address) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters

    /**
     * Returns the unique ID of the guest.
     *
     * @return the guest ID.
     */
    public int getGuestId() {
        return guestId;
    }

    /**
     * Returns the name of the guest.
     *
     * @return the guest's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the email address of the guest.
     *
     * @return the guest's email address.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the phone number of the guest.
     *
     * @return the guest's phone number.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Returns the address of the guest.
     *
     * @return the guest's address.
     */
    public String getAddress() {
        return this.address;
    }

    // Setters

    /**
     * Sets the unique ID of the guest.
     *
     * @param guestId the new guest ID.
     */
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    /**
     * Sets the name of the guest.
     *
     * @param name the new name of the guest.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the email address of the guest.
     *
     * @param email the new email address of the guest.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone number of the guest.
     *
     * @param phone the new phone number of the guest.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the address of the guest.
     *
     * @param addr the new address of the guest.
     */
    public void setAddr(String addr) {
        this.address = addr;
    }
}
