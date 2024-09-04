import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
    public static int counter = 1;
    private int id;
    private Room room;
    private Client client;
    private Date startDate;
    private Date endDate;
    private static List<Reservation> reservations = new ArrayList<>();

    public Reservation(Room room, Client client, Date startDate, Date endDate) {
        this.id = counter++;
        this.room = room;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        client.addReservation(this);
    }


    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation [ID: " + id + ", Room: " + room + ", Client: " + client +
                ", Start Date: " + startDate + ", End Date: " + endDate + "]";
    }


    public static List<Reservation> getReservations() {
        return reservations;
    }

    public static boolean createReservation(Room room, Client client, Date startDate, Date endDate) {
        if (!isValidDate(startDate, endDate)) {
            System.out.println("Invalid date range: Start date must be today or later, and before end date.");
            return false;
        }

        if (isRoomAvailable(room, startDate, endDate)) {
            room.setAvailable(false);
            Reservation newRes = new Reservation(room, client, startDate, endDate);
            reservations.add(newRes);
            System.out.println("New Reservation created: " + newRes);
            return true;
        } else {
            System.out.println("Room " + room.getNumber() + " is not available for the selected dates.");
            return false;
        }
    }

    public static boolean updateReservation(int id, Room newRoom, Client newClient, Date newStartDate, Date newEndDate) {
        if (!isValidDate(newStartDate, newEndDate)) {
            System.out.println("Invalid date range: Start date must be today or later, and before end date.");
            return false;
        }

        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                if (isRoomAvailable(newRoom, newStartDate, newEndDate) || reservation.getRoom().getNumber() == newRoom.getNumber()) {
                    reservation.setRoom(newRoom);
                    reservation.setClient(newClient);
                    reservation.setStartDate(newStartDate);
                    reservation.setEndDate(newEndDate);
                    System.out.println("Reservation updated: " + reservation);
                    return true;
                } else {
                    System.out.println("Room " + newRoom.getNumber() + " is not available for the selected dates.");
                    return false;
                }
            }
        }

        System.out.println("Reservation not found with ID: " + id);
        return false;
    }

    public static boolean cancelReservation(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                reservations.remove(reservation);
                reservation.getRoom().setAvailable(true);
                reservation.getClient().removeReservation(id);
                System.out.println("Reservation canceled: " + reservation);
                return true;
            }
        }
        System.out.println("Reservation not found with ID: " + id);
        return false;
    }

    public static Reservation readReservation(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                System.out.println("Reservation found: " + reservation);
                return reservation;
            }
        }
        System.out.println("Reservation not found with ID: " + id);
        return null;
    }


    private static boolean isRoomAvailable(Room room, Date startDate, Date endDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getNumber() == room.getNumber() &&
                    (startDate.before(reservation.getEndDate()) && endDate.after(reservation.getStartDate()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidDate(Date startDate, Date endDate) {
        Date today = new Date();
        return (startDate.after(today) || startDate.equals(today)) && startDate.before(endDate);
    }
}
