import java.util.ArrayList;

public class Customer {
    int tlfnr;
    String name;
    ArrayList<int[]> bookings;

    public Customer() {
        bookings = new ArrayList<>();
    }
}
