import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Client {
    private static int counter = 1;
    private int id;
    private String name;
    private String email;
    private String phone;
    private Map<Integer, Reservation> reservations;
    public Client(String name, String email, String phone) {
        this.id = counter++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.reservations=new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {

        return email;
    }

    public String getPhone() {

        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    public void addReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    public void removeReservation(int reservationId) {
        reservations.remove(reservationId);
    }
    public void displayReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found for client " + name);
        } else {
            System.out.println("Reservations for client " + name + ":");
            for (Reservation reservation : reservations.values()) {
                System.out.println(reservation);
            }
        }
    }
}
