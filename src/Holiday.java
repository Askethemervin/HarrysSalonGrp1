import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Holiday {

    static String date1;
    static String date2;

    static int date1int;
    static int date2int;
    static void holiday() throws IOException {


        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        boolean korrektinput = true;

        System.out.println("Start dato for ferien (yyyy/MM/dd)");

        while (korrektinput) {

            date1 = input1.nextLine();
            if (Main.dates.contains(date1)) {

                korrektinput = false;

            } else System.out.println("Du har indtastet en utilgængelig dato");

        }
        System.out.println("Slut dato for ferien (yyyy/MM/dd)");

        korrektinput = true;

        while (korrektinput) {

            date2 = input2.nextLine();

            if (Main.dates.contains(date2) && Main.dates.indexOf(date1) <= Main.dates.indexOf(date2)) {

                korrektinput = false;
            } else System.out.println("Du har indtastet en utilgængelig dato");

        }

        date1int = Main.dates.indexOf(date1);
        date2int = Main.dates.indexOf(date2);
        boolean e = true;
        boolean f = true;
        ArrayList<String> ls = new ArrayList<>();

        for (int i = date1int; i <= date2int; i++) {

            for (int j = 1; j <= 16; j++) {

                if (!Main.calender.get(i)[j].equals("0")) {

                    e = false;
                    ls.add(Main.dates.get(i) + " " + Available.timeindex(j) + " telnr: " + Main.calender.get(i)[j]);

                }
            }
        }
        if (!e) {

            System.out.println("Følgende tider er bookede i det givne tidsrum");

            for (String g:ls)
            { System.out.println(g);}

            System.out.println("Ønsker stadig at oprette ferien?");

            Menu.menu(Main.janej);

            if (Menu.op == 2) {

                f = false;

            }

        }

        if (f){
            for (int i = date1int; i <= date2int; i++) {

                for (int j = 1; j <= 16; j++) {

                    String telnr = Main.calender.get(i)[j];
                    if (!telnr.equals("0") && !telnr.equals("1")) {
                        for (int l = Main.customers.get(Main.phoneNumbers.indexOf(telnr)).bookings.size() - 1; l >= 0; l--) {
                            if (Arrays.equals((new String[]{Main.dates.get(i), Available.timeindex(j)}), Main.customers.get(Main.phoneNumbers.indexOf(telnr)).bookings.get(l))) {
                                Main.customers.get(Main.phoneNumbers.indexOf(telnr)).bookings.remove(l);
                            }

                        }
                    }
                    Main.calender.get(i)[j] = "1";
                }
            }

            ToFile.saveList(Main.calender, "calender.txt");
            ToFile.saveCustomer(Main.customers);
            String[] holiday = new String[]{date1, date2};
            Main.holidays.add(holiday);
            ToFile.saveList(Main.holidays, "holidays.txt");
            System.out.println("Din ferie er nu oprettet i kalenderen");
        }
    }


    static void changeHoliday() throws IOException {
        int numbers=1;
        for(String[] s: Main.holidays){
            System.out.println(numbers + ": " + Arrays.toString(s));
            numbers++;
        }
        System.out.println("Vil du slette ferie?");
        Menu.menu(Main.janej);
        if(Menu.op==1){
            System.out.println("Indtast nr for ferie [1,"+Main.holidays.size()+"]");
            int choice = Menu.inInt(Main.holidays.size())-1;

        date1 = Main.holidays.get(choice)[0];
        date2 = Main.holidays.get(choice)[1];

        date1int = Main.dates.indexOf(date1);
        date2int = Main.dates.indexOf(date2);

        for (int i = date1int; i <= date2int; i++) {

            for (int j = 1; j <= 16; j++) {

                Main.calender.get(i)[j] = "0";


            }

        }

        ToFile.saveList(Main.calender, "calender.txt");


        Main.holidays.remove(choice);
        ToFile.saveList(Main.holidays, "holidays.txt");
            System.out.println("Din ferie er nu slettet fra kalenderen");
        }
    }
}




