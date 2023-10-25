import java.io.IOException;
import java.util.Arrays;

public class Book {

    static int y;
//    static List<String[]> records = new ArrayList<>();
//    static String[] dage = new String[17];
//
    public static void main(String[] args) throws IOException {

//        try (BufferedReader br = new BufferedReader(new FileReader("csv"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                records.add(values);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }



    static void book(String date, String time, String telnr) throws IOException {

        switch (time) {
            case "10:00" -> y=1;

            case "10:30" -> y=2;

            case "11:00" -> y=3;

            case "11:30" -> y=4;

            case "12:00" -> y=5;

            case "12:30" -> y=6;

            case "13:00" -> y=7;

            case "13:30" -> y=8;

            case "14:00" -> y=9;

            case "14:30" -> y=10;

            case "15:00" -> y=11;

            case "15:30" -> y=12;

            case "16:00" -> y=13;

            case "16:30" -> y=14;

            case "17:00" -> y=15;

            case "17:30" -> y=16;
        }


        Main.calender.get(Arrays.asList(Main.dates).indexOf(date))[y]=telnr;

        ToFile.saveList(Main.calender);


        Main.customers.get(Main.phoneNumbers.indexOf(telnr)).bookings.add(new String[]{date,time});

        ToFile.saveCustomer(Main.customers);



    }






}
