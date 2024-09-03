import  java.util.ArrayList;
import java.util.Date;
import  java.util.List;
public class Reservation {
    public static int counter=1;
    private int id;
    private Room room;
    private Client client;
    private Date startDate;
    private Date endDate;
    private  static  List<Reservation> reservations=new ArrayList();
    public Reservation(Room room, Client client, Date startDate, Date endDate) {
        this.id=counter++;
        this.room=room;
        this.client=client;
        this.startDate=startDate;
        this.endDate=endDate;
    }
    public int getId() {

        return id;
    }
    public void setId(int id) {

        this.id=id;
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
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {

        this.startDate=startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {

        this.endDate=endDate;
    }

    @Override
    public String toString() {
        return "the reservation is done \n"+room+" for the client "+client;
    }
    public static List<Reservation> getReservations() {

        return  reservations;
    }
    public static void createReservation(Room room, Client client, Date startDate , Date endDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getNumber() == room.getNumber() && reservation.getStartDate().equals(startDate)) {
                System.out.println("Room " + room.getNumber() + " is not available on " + startDate);
                return;
            }
        }
        room.setAvailable(false);
        Reservation newRes = new Reservation(room, client, startDate, endDate);
        reservations.add(newRes);
        System.out.println("New Reservation created" + newRes);
    }
    public static boolean updateReservation(int id, Room nRoom, Client nClient, Date nStartDate, Date nEndDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                for (Reservation existingReservation : reservations) {
                    if (existingReservation.getRoom().getNumber() == nRoom.getNumber() &&
                            existingReservation.getStartDate().equals(nStartDate) &&
                            existingReservation.getId() != id) {
                        System.out.println("Room " + nRoom.getNumber() + " is not available on " + nStartDate+ "to "+nEndDate);
                        return false;
                    }
                }

                reservation.setRoom(nRoom);
                reservation.setClient(nClient);
                reservation.setStartDate(nStartDate);
                reservation.setEndDate(nEndDate);
                System.out.println("Reservation updated: " + reservation);
                return true;
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
}