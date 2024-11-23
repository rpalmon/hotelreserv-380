package main;

import java.util.Scanner;
import main.Room.RoomType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The HotelSystem class provides functionalities for managing a hotel reservation system,
 * including managing guests, rooms, and reservations, as well as user authentication.
 */
public class HotelSystem {
    private GuestData guestData;
    private RoomData roomData;
    private ReservationData resData;
    private UserData userData;
    private Scanner sc;

    /**
     * Constructor for the HotelSystem class.
     * Initializes the data management objects and input scanner.
     */
    public HotelSystem() {
        guestData = new GuestData();
        roomData = new RoomData();
        resData = new ReservationData();
        userData = new UserData();
        sc = new Scanner(System.in);
    }

    /**
     * Starts the hotel reservation system by prompting user login and providing menu options.
     */
    public void start() {
        System.out.println("Hotel Reservation System is starting.");
        System.out.println("Enter username:");
        String userN = sc.nextLine();
        System.out.println("Enter password:");
        String userP = sc.nextLine();

        if (userData.validUser(userN, userP)) {
            boolean run = true;
            while (run) {
                System.out.println("\nChoose option:");
                System.out.println("1 add guest");
                System.out.println("2 search guest");
                System.out.println("3 update guest");
                System.out.println("4 delete guest");
                System.out.println("5 quit");
                System.out.println("6 add room");
                System.out.println("7 delete room");
                System.out.println("8 update room");
                System.out.println("9 find room details");
                System.out.println("10 create reservation");
                System.out.println("11 delete reservation");
                System.out.println("12 update reservation");
                System.out.println("13 find reservation");
                int option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 1 -> addGuest();
                    case 2 -> searchGuest();
                    case 3 -> updateGuest();
                    case 4 -> delGuest();
                    case 5 -> {
                        run = false;
                        System.out.println("stopping");
                    }
                    case 6 -> addRoom();
                    case 7 -> delRoom();
                    case 8 -> updateRoom();
                    case 9 -> findRoom();
                    case 10 -> addRes();
                    case 11 -> delRes();
                    case 12 -> updateRes();
                    case 13 -> findRes();
                    default -> System.out.println("Invalid operation");
                }
            }
        } else {
            System.out.println("Invalid Username or Password.");
        }
    }

    /**
     * Adds a new guest by prompting the user for guest details.
     */
    private void addGuest() {
        System.out.println("guest\n name");
        String gName = sc.nextLine();
        System.out.println("email:");
        String gEmail = sc.nextLine();
        System.out.println("phone:");
        String gPhone = sc.nextLine();
        System.out.println("address:");
        String gAddress = sc.nextLine();

        Guest guest = new Guest(0, gName, gEmail, gPhone, gAddress);
        guestData.addGuest(guest);
    }

    /**
     * Searches for a guest by ID and displays their details if found.
     */
    private void searchGuest() {
        System.out.println("Enter Guest ID:");
        int id = Integer.parseInt(sc.nextLine());
        Guest guest = guestData.getGuestID(id);
        if (guest != null) {
            System.out.println("Details: " + guest.getName());
        } else {
            System.out.println("Guest not found.");
        }
    }

    /**
     * Updates an existing guest's details.
     */
    private void updateGuest() {
        System.out.println("Enter Guest ID to update:");
        int gID = Integer.parseInt(sc.nextLine());
        System.out.println("Guest name:");
        String gName = sc.nextLine();
        System.out.println("Email:");
        String gEmail = sc.nextLine();
        System.out.println("Phone:");
        String gPhone = sc.nextLine();
        System.out.println("Address:");
        String gAddress = sc.nextLine();

        Guest guest = new Guest(gID, gName, gEmail, gPhone, gAddress);
        guestData.updateGuest(guest);
    }

    /**
     * Deletes a guest by ID.
     */
    private void delGuest() {
        System.out.println("Enter Guest ID to delete:");
        int guestID = Integer.parseInt(sc.nextLine());
        guestData.delGuest(guestID);
    }

    /**
     * Adds a new room by prompting the user for room details.
     */
    public void addRoom() {
        System.out.println("Room\n number:");
        String rNum = sc.nextLine();
        System.out.println("Type:");
        Room.RoomType rType = Room.RoomType.valueOf("SINGLE"); // Example default
        System.out.println("Price:");
        double rPrice = Double.parseDouble(sc.nextLine());
        Boolean rAvail = true;
        Room room = new Room(0, rNum, rType, rPrice, rAvail);
        roomData.addRoom(room);
    }

    /**
     * Deletes a room by ID.
     */
    public void delRoom() {
        System.out.println("Enter Room ID to delete:");
        int roomID = Integer.parseInt(sc.nextLine());
        roomData.delRoom(roomID);
    }

    /**
     * Updates an existing room's details.
     */
    public void updateRoom() {
        System.out.println("Room\n ID:");
        int rID = Integer.parseInt(sc.nextLine());
        System.out.println("Number:");
        String rNum = sc.nextLine();
        System.out.println("Type:");
        Room.RoomType rType = Room.RoomType.valueOf("SINGLE"); // Example default
        System.out.println("Price:");
        double rPrice = Double.parseDouble(sc.nextLine());
        System.out.println("Available (true/false):");
        Boolean rAvail = Boolean.parseBoolean(sc.nextLine());
        Room room = new Room(rID, rNum, rType, rPrice, rAvail);
        roomData.updateRoom(room);
    }

    /**
     * Finds room details by ID.
     */
    public void findRoom() {
        System.out.println("Enter Room ID:");
        int id = Integer.parseInt(sc.nextLine());
        Room room = roomData.getRoomID(id);
        if (room != null) {
            System.out.println("Details: " + room.toString());
        } else {
            System.out.println("Room not found.");
        }
    }

    /**
     * Adds a new reservation by prompting the user for reservation details.
     */
    public void addRes() {
        System.out.println("Reservation\n Guest ID:");
        int resGuestID = Integer.parseInt(sc.nextLine());

        System.out.println("Room ID:");
        int resRoomID = Integer.parseInt(sc.nextLine());

        System.out.println("Check-in date (YYYY-MM-DD):");
        LocalDate checkInDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("Check-out date (YYYY-MM-DD):");
        LocalDate checkOutDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("Total cost:");
        double totalCost = Double.parseDouble(sc.nextLine());

        System.out.println("Reservation status (BOOKED, CHECKEDIN, CHECKEDOUT, CANCELLED):");
        Reservation.Status status = Reservation.Status.valueOf(sc.nextLine().toUpperCase());

        System.out.println("Payment status (true/false):");
        boolean paymentStatus = Boolean.parseBoolean(sc.nextLine());

        Reservation res = new Reservation(0, resGuestID, resRoomID, checkInDate, checkOutDate, totalCost, status, paymentStatus);
        resData.addRes(res);

        System.out.println("Reservation was successfully created.");
    }

    /**
     * Deletes a reservation by ID.
     */
    public void delRes() {
        System.out.println("Enter Reservation ID to delete:");
        int resID = Integer.parseInt(sc.nextLine());
        resData.delRes(resID);
    }

    /**
     * Updates an existing reservation's details.
     */
    public void updateRes() {
        System.out.println("Enter Reservation ID:");
        int resId = Integer.parseInt(sc.nextLine());

        System.out.println("Enter Guest ID:");
        int guestId = Integer.parseInt(sc.nextLine());

        System.out.println("Enter Room ID:");
        int roomId = Integer.parseInt(sc.nextLine());

        System.out.println("Enter Check-in Date (YYYY-MM-DD):");
        LocalDate checkInDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("Enter Check-out Date (YYYY-MM-DD):");
        LocalDate checkOutDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("Enter Total Cost:");
        double totalCost = Double.parseDouble(sc.nextLine());

        System.out.println("Enter Reservation Status (BOOKED, CHECKEDIN, CHECKEDOUT, CANCELLED):");
        Reservation.Status status = Reservation.Status.valueOf(sc.nextLine().toUpperCase());

        System.out.println("Enter Payment Status (true/false):");
        boolean paymentStatus = Boolean.parseBoolean(sc.nextLine());

        Reservation res = new Reservation(resId, guestId, roomId, checkInDate, checkOutDate, totalCost, status, paymentStatus);
        resData.updateRes(res);

        System.out.println("Reservation updated successfully.");
    }

	    /**
	     * Finds a reservation by ID and displays its details.
	     */
	    public void findRes() {
	        System.out.println("Enter Reservation ID:");
	        int resID = Integer.parseInt(sc.nextLine());
	        Reservation res = resData.getRes(resID);
	        if (res != null) {
	            System.out.println("Reservation found: " + res.toString());
	        } else {
	            System.out.println("Reservation not found.");
	        }
	    }
	
	    /**
	     * Validates a user's credentials (placeholder method).
	     */
	    public void validUser() {
	        // Placeholder method
	    }

    /**
     * Main entry point for the application.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        HotelSystem sys = new HotelSystem();
        sys.start();
    }
}
