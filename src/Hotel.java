import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private String name;
    private String address;
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean addRoom(Room room) {
        if (Room.findRoomByNum(room.getNumber()) == null) {
            rooms.add(room);
            Room.createRoom(room.getNumber(), room.getType(), room.isAvailable());
            System.out.println("Room added to hotel: " + room);
            return true;
        } else {
            System.out.println("Room number " + room.getNumber() + " already exists in the hotel.");
            return false;
        }
    }

    public boolean removeRoom(int roomNumber) {
        Room room = Room.findRoomByNum(roomNumber);
        if (room != null && rooms.contains(room)) {
            rooms.remove(room);
            Room.deleteRoom(roomNumber);
            System.out.println("Room removed from hotel: " + room);
            return true;
        }
        System.out.println("Room not found with number: " + roomNumber + " in this hotel.");
        return false;
    }

    public void displayAvailRooms(RoomType type) {
        System.out.println("Available rooms of type " + type + " in hotel " + name + ":");
        for (Room room : rooms) {
            if (room.getType() == type && room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public boolean addRes(Room room, Client client, Date startDate, Date endDate) {
        if (rooms.contains(room)) {
            if (Reservation.createReservation(room, client, startDate, endDate)) {
                Reservation newReservation = new Reservation(room, client, startDate, endDate);
                reservations.add(newReservation);
                System.out.println("Reservation added to hotel: " + newReservation);
                return true;
            } else {
                System.out.println("Reservation failed: Room is not available or date is invalid.");
                return false;
            }
        } else {
            System.out.println("Room not found in this hotel: " + room);
            return false;
        }
    }

    public boolean removeRes(int reservationId) {
        Reservation reservation = findResById(reservationId);
        if (reservation != null) {
            reservations.remove(reservation);
            Reservation.cancelReservation(reservationId);
            System.out.println("Reservation removed from hotel: " + reservation);
            return true;
        }
        System.out.println("Reservation not found with ID: " + reservationId + " in this hotel.");
        return false;
    }

    public Reservation findResById(int resId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == resId) {
                return reservation;
            }
        }
        return null;
    }

    public void listAllReservations() {
        System.out.println("Reservations for hotel " + name + ":");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    @Override
    public String toString() {
        return "Hotel{name='" + name + "', address='" + address + "', rooms=" + rooms.size() +
                ", reservations=" + reservations.size() + "}";
    }
}
