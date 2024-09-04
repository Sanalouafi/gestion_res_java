import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Hotel hotel;
    private SimpleDateFormat dateFormat;

    public Menu() {
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
    }

    public void start() {
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Enter Hotel Details");
            System.out.println("2. Add Room");
            System.out.println("3. Create Reservation");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Update Reservation");
            System.out.println("6. List All Reservations");
            System.out.println("7. Display Available Rooms");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    enterHotel();
                    break;
                case 2:
                    addRoom();
                    break;
                case 3:
                    createRes();
                    break;
                case 4:
                    cancelRes();
                    break;
                case 5:
                    updateRes();
                    break;
                case 6:
                    listAllRes();
                    break;
                case 7:
                    displayAvailableRooms();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    private void enterHotel() {
        System.out.print("Enter hotel name: ");
        String name = scanner.nextLine();
        System.out.print("Enter hotel address: ");
        String address = scanner.nextLine();
        hotel = new Hotel(name, address);
        System.out.println("Hotel added: " + hotel);
    }

    private void addRoom() {
        if (hotel == null) {
            System.out.println("Please enter hotel details first.");
            return;
        }

        System.out.print("Enter room number: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter room type (Simple, Double, Suite): ");
        RoomType type = RoomType.valueOf(scanner.nextLine());
        System.out.print("Is the room available (true/false): ");
        boolean isAvailable = scanner.nextBoolean();
        scanner.nextLine();

        Room.createRoom(number, type, isAvailable);
    }

    private void createRes() {
        if (hotel == null) {
            System.out.println("Please enter hotel details first.");
            return;
        }

        System.out.print("Enter room number: ");
        int number = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room room = Room.findRoomByNum(number);
        if (room == null || !room.isAvailable()) {
            System.out.println("Room not available or does not exist.");
            return;
        }

        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        System.out.print("Enter client email: ");
        String clientEmail = scanner.nextLine();
        System.out.print("Enter client phone: ");
        String clientPhone = scanner.nextLine();
        Client client = new Client(clientName, clientEmail, clientPhone);

        System.out.print("Enter start date (yyyy-MM-dd): ");
        Date startDate = parseDate(scanner.nextLine());
        System.out.print("Enter end date (yyyy-MM-dd): ");
        Date endDate = parseDate(scanner.nextLine());

        if (startDate != null && endDate != null) {
            Reservation.createReservation(room, client, startDate, endDate);
        }
    }


    private void cancelRes() {
        System.out.print("Enter reservation ID to cancel: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Reservation.cancelReservation(id);
    }

    private void updateRes() {
        System.out.print("Enter reservation ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new room number: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        Room room = Room.findRoomByNum(number);

        System.out.print("Enter new client name: ");
        String clientName = scanner.nextLine();
        System.out.print("Enter new client email: ");
        String clientEmail = scanner.nextLine();
        System.out.print("Enter new client phone: ");
        String clientPhone = scanner.nextLine();
        Client client = new Client(clientName, clientEmail, clientPhone);

        System.out.print("Enter new start date (yyyy-MM-dd): ");
        Date startDate = parseDate(scanner.nextLine());
        System.out.print("Enter new end date (yyyy-MM-dd): ");
        Date endDate = parseDate(scanner.nextLine());

        if (startDate != null && endDate != null) {
            Reservation.updateReservation(id, room, client, startDate, endDate);
        }
    }

    private void listAllRes() {

        Reservation.getReservations().forEach(System.out::println);
    }

    private void displayAvailableRooms() {
        System.out.print("Enter room type (Simple, Double, Suite): ");
        RoomType type = RoomType.valueOf(scanner.nextLine());
        hotel.displayAvailRooms(type);
    }

    private Date parseDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }
}
