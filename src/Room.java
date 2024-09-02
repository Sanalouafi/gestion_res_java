import java.util.ArrayList;
import java.util.List;

enum RoomType {
    Simple,
    Double,
    Suite;
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

    public static void createRoom(int number, RoomType type, boolean isAvailable) {
        Room room = new Room(number, type, isAvailable);
        rooms.add(room);
        System.out.println("Room created: " + room);
    }

    public static List<Room> readAllRooms() {
        return new ArrayList<>(rooms);
    }

    public static boolean updateRoom(int number, RoomType newType, boolean newAvailability) {
        for (Room room : rooms) {
            if (room.getNumber() == number) {
                room.setType(newType);
                room.setAvailable(newAvailability);
                System.out.println("Room updated: " + room);
                return true;
            }
        }
        System.out.println("Room not found with number: " + number);
        return false;
    }

    public static boolean deleteRoom(int number) {
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (room.getNumber() == number) {
                rooms.remove(i);
                System.out.println("Room deleted: " + room);
                return true;
            }
        }
        System.out.println("Room not found with number: " + number);
        return false;
    }
}
