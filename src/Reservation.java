import  java.util.ArrayList;
import java.util.Date;
import  java.util.List;
public class Reservation {
    public static int counter=1;
    private int id;
    private Room room;
    private Client client;
    private Date date;
    private  static  List<Reservation> reservations=new ArrayList();
    public Reservation(Room room, Client client, Date date) {
        this.id=counter++;
        this.room=room;
        this.client=client;
        this.date=date;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client=client;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room=room;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date=date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id=id;
    }
    @Override
    public String toString() {
        return "the reservation is done \n"+room+" for the client "+client;
    }
    public static List<Reservation> getReservations() {
        return  reservations;
    }
    public static void createReservation(Room room, Client client, Date date) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getNumber() == room.getNumber() && reservation.getDate().equals(date)) {
                System.out.println("Room " + room.getNumber() + " is not available on " + date);
                return;
            }
        }
        room.setAvailable(false);
        Reservation newRes = new Reservation(room, client, date);
        reservations.add(newRes);
        System.out.println("New Reservation created" + newRes);
    }
    public static boolean updateReservation(int id, Room nRoom, Client nClient, Date nDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                for (Reservation existingReservation : reservations) {
                    if (existingReservation.getRoom().getNumber() == nRoom.getNumber() &&
                            existingReservation.getDate().equals(nDate) &&
                            existingReservation.getId() != id) {
                        System.out.println("Room " + nRoom.getNumber() + " is not available on " + nDate);
                        return false;
                    }
                }

                reservation.setRoom(nRoom);
                reservation.setClient(nClient);
                reservation.setDate(nDate);
                System.out.println("Reservation updated: " + reservation);
                return true;
            }
        }

        System.out.println("Reservation not found with ID: " + id);
        return false;
    }

}