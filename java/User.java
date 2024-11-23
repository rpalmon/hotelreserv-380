package main;

/**
 * The {@code User} class represents a user in the system.
 * It contains details such as the user's ID, username, password, email, and phone number.
 */
public class User {

    /** The unique ID of the user. */
    private int userID;

    /** The username of the user. */
    private String userName;

    /** The password of the user. */
    private String userPass;

    /** The email address of the user. */
    private String userEmail;

    /** The phone number of the user. */
    private String userPhone;

    /**
     * Constructs a new {@code User} object with the specified details.
     *
     * @param userID the unique ID of the user.
     * @param userName the username of the user.
     * @param userPass the password of the user.
     * @param userEmail the email address of the user.
     * @param userPhone the phone number of the user.
     */
    public User(int userID, String userName, String userPass, String userEmail, String userPhone) {
        this.userID = userID;
        this.userName = userName;
        this.userPass = userPass;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    // Setters

    /**
     * Sets the username of the user.
     *
     * @param name the new username.
     */
    public void setUserName(String name) {
        this.userName = name;
    }

    /**
     * Sets the password of the user.
     *
     * @param pass the new password.
     */
    public void setUsePass(String pass) {
        this.userPass = pass;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the new email address.
     */
    public void setUseEmail(String email) {
        this.userEmail = email;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phone the new phone number.
     */
    public void setUsePhone(String phone) {
        this.userPhone = phone;
    }

    // Getters

    /**
     * Returns the unique ID of the user.
     *
     * @return the user ID.
     */
    public int getUserID() {
        return this.userID;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username.
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password.
     */
    public String getUserPass() {
        return this.userPass;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address.
     */
    public String getUserEmail() {
        return this.userEmail;
    }

    /**
     * Returns the phone number of the user.
     *
     * @return the phone number.
     */
    public String getUserPhone() {
        return this.userPhone;
    }
}
