import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Book {

    static int y;
    static String debt;

    static int timeSwitch(String time){
        switch (time) {
            case "10:00" -> {
                return 1;
            }

            case "10:30" -> {
                return 2;
            }

            case "11:00" -> {
                return 3;
            }

            case "11:30" -> {
                return 4;
            }

            case "12:00" -> {
                return 5;
            }

            case "12:30" -> {
                return 6;
            }

            case "13:00" -> {
                return 7;
            }

            case "13:30" -> {
                return 8;
            }

            case "14:00" -> {
                return 9;
            }

            case "14:30" -> {
                return 10;
            }

            case "15:00" -> {
                return 11;
            }

            case "15:30" -> {
                return 12;
            }

            case "16:00" -> {
                return 13;
            }

            case "16:30" -> {
                return 14;
            }

            case "17:00" -> {
                return 15;
            }

            case "17:30" -> {
                return 16;
            }
        }
        return 0;
    }

    static void book(String date, String time, String telnr) throws IOException {

        Main.calender.get(Main.dates.indexOf(date))[timeSwitch(time)]=telnr;

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

        y = timeSwitch(time);

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

        Main.payments.get(Main.dates.indexOf(date))[timeSwitch(time)-1] = price;

        ToFile.saveList(Main.payments, "PaymentCalender.txt");

    }

    static int isPayed(String date, String time) {

        y=timeSwitch(time);

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
    static String payedPrice(String date, String time) {

        return Integer.toString(Integer.parseInt(Main.payments.get(Main.dates.indexOf(date))[ timeSwitch(time) - 1]));

    }
}
