import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class ToFile {

    public static void saveList(List<String[]> dataLines) throws IOException {
        FileWriter fil = new FileWriter("calender.txt");
        PrintWriter ud = new PrintWriter(fil);
        for (String[] s: dataLines){
            ud.println(String.join(",",s));
        }
        fil.close();
    }
    public static void saveCustomer(List<Customer> dataLines) throws IOException {
        FileWriter fil = new FileWriter("customers.txt");
        PrintWriter ud = new PrintWriter(fil);

        for (Customer k: dataLines) {
            ArrayList<String> bookArr = new ArrayList<>();
            for (String[] s: k.bookings){


                bookArr.add(String.join(".",s));

            }

            ud.println(k.tlfnr +","+ k.name +","+ String.join(";",bookArr));
        }
        fil.close();
    }
}
