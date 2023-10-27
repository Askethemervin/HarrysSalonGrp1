import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Book {

    static int y;
    static String debt;

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


        Main.calender.get(Main.dates.indexOf(date))[y]=telnr;

        ToFile.saveList(Main.calender,"calender.txt");


        if (Main.phoneNumbers.contains(telnr)){
            Main.customers.get(Main.phoneNumbers.indexOf(telnr)).bookings.add(new String[]{date, time});
        }
        else {
            System.out.println("Kunde skal oprettes, angiv navn: ");
            String name = Main.input.nextLine();
            String[] datetime = new String[]{date,time};
            ArrayList<String[]> datetimeAL = new ArrayList<>();
            datetimeAL.add(datetime);
            Main.customers.add(new Customer(telnr, name, datetimeAL));
        }

        ToFile.saveCustomer(Main.customers);



    }

    static void delete(String date, String time) throws IOException {

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

        String telnr = Main.calender.get(Main.dates.indexOf(date))[y];

        Main.calender.get(Main.dates.indexOf(date))[y]="0";

        ToFile.saveList(Main.calender,"calender.txt");



        for (int i=0; i<Main.customers.get(Main.phoneNumbers.indexOf(telnr)).bookings.size(); i++){
            if (Arrays.equals((new String[]{date, time}),Main.customers.get(Main.phoneNumbers.indexOf(telnr)).bookings.get(i))){
                Main.customers.get(Main.phoneNumbers.indexOf(telnr)).bookings.remove(i);
            }

        }



        ToFile.saveCustomer(Main.customers);



    }

    static void pay(String date, String time, String price) throws IOException {

        switch (time) {
            case "10:00" -> y = 1;

            case "10:30" -> y = 2;

            case "11:00" -> y = 3;

            case "11:30" -> y = 4;

            case "12:00" -> y = 5;

            case "12:30" -> y = 6;

            case "13:00" -> y = 7;

            case "13:30" -> y = 8;

            case "14:00" -> y = 9;

            case "14:30" -> y = 10;

            case "15:00" -> y = 11;

            case "15:30" -> y = 12;

            case "16:00" -> y = 13;

            case "16:30" -> y = 14;

            case "17:00" -> y = 15;

            case "17:30" -> y = 16;
        }


        Main.payments.get(Main.dates.indexOf(date))[y-1] = price;

        ToFile.saveList(Main.payments, "PaymentCalender.txt");




    }
    static int isPayed(String date, String time) throws IOException {

        switch (time) {
            case "10:00" -> y = 1;

            case "10:30" -> y = 2;

            case "11:00" -> y = 3;

            case "11:30" -> y = 4;

            case "12:00" -> y = 5;

            case "12:30" -> y = 6;

            case "13:00" -> y = 7;

            case "13:30" -> y = 8;

            case "14:00" -> y = 9;

            case "14:30" -> y = 10;

            case "15:00" -> y = 11;

            case "15:30" -> y = 12;

            case "16:00" -> y = 13;

            case "16:30" -> y = 14;

            case "17:00" -> y = 15;

            case "17:30" -> y = 16;
        }

        if (Integer.parseInt(Main.payments.get(Main.dates.indexOf(date))[y - 1])==0){
            return 0;
        } else if (Integer.parseInt(Main.payments.get(Main.dates.indexOf(date))[y - 1])>0) {
            return 1;
        }
        else {
            debt = Integer.toString(Integer.parseInt(Main.payments.get(Main.dates.indexOf(date))[y - 1])*-1);
            return -1;
        }


    }
}
