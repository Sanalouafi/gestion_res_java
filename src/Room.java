import java.util.ArrayList;
import java.util.List;

enum RoomType {
    Simple,
    Double,
    Suite
}

public class Room {
    private int number;
    private RoomType type;
    private boolean isAvailable;

    private static List<Room> rooms = new ArrayList<>();

    public Room(int number, RoomType type, boolean isAvailable) {
        this.number = number;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" + "number=" + number + ", type=" + type + ", isAvailable=" + isAvailable + '}';
    }

    // Static methods for managing rooms
    public static boolean createRoom(int number, RoomType type, boolean isAvailable) {
        if (findRoomByNumber(number) != null) {
            System.out.println("Room creation failed: Room number " + number + " already exists.");
            return false;
        }
        Room room = new Room(number, type, isAvailable);
        rooms.add(room);
        System.out.println("Room created: " + room);
        return true;
    }

    public static List<Room> readAllRooms() {
        return new ArrayList<>(rooms);
    }

    public static boolean updateRoom(int number, RoomType newType, boolean newAvailability) {
        Room room = findRoomByNumber(number);
        if (room != null) {
            room.setType(newType);
            room.setAvailable(newAvailability);
            System.out.println("Room updated: " + room);
            return true;
        }
        System.out.println("Room not found with number: " + number);
        return false;
    }

    public static boolean deleteRoom(int number) {
        Room room = findRoomByNumber(number);
        if (room != null) {
            rooms.remove(room);
            System.out.println("Room deleted: " + room);
            return true;
        }
        System.out.println("Room not found with number: " + number);
        return false;
    }

    public static Room findRoomByNumber(int number) {
        for (Room room : rooms) {
            if (room.getNumber() == number) {
                return room;
            }
        }
        return null;
    }

    public static List<Room> findAvailableRooms(RoomType type) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getType() == type && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public static boolean checkRoomAvailability(int number) {
        Room room = findRoomByNumber(number);
        if (room != null && room.isAvailable()) {
            return true;
        }
        return false;
    }
}
