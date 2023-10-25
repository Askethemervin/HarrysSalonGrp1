import java.util.ArrayList;

public class Customer {
    int tlfnr;
    String name;
    ArrayList<int[]> bookings;

    public Customer(int a, String b) {
        bookings = new ArrayList<>();
        name=b;
        tlfnr=a;

    }
}
