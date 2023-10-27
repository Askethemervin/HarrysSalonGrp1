import java.util.ArrayList;


public class Customer {
    String tlfnr;
    String name;
    ArrayList<String[]> bookings;

    public Customer(String a, String b, ArrayList<String[]> c) {
        bookings = c;
        name=b;
        tlfnr=a;
        Main.phoneNumbers.add(a);

    }

    @Override
    public String toString() {
        return tlfnr+","+name+","+bookings;
    }
}
