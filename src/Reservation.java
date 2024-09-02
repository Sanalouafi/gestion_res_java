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
        return "";
    }
}
