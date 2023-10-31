import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Holiday {


    static void holiday() throws IOException {

        String date1 = "";
        String date2 = "";


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

            if (Main.dates.contains(date2)) {

                korrektinput = false;
            } else System.out.println("Du har indtastet en utilgængelig dato");

        }

        int date11 = Main.dates.indexOf(date1);
        int date22 = Main.dates.indexOf(date2);
        int diff = date22 - date11;

        for (int i = 0; i <= diff; i++) {

            for (int j = 1; j <= 16; j++) {

                Main.calender.get(date11 + i)[j] = "1";


            }

        }

        ToFile.saveList(Main.calender, "calender.txt");

        String[] holiday = new String[]{date1, date2};
        Main.holidays.add(holiday);
        ToFile.saveList(Main.holidays, "holidays.txt");
        System.out.println("Din ferie er nu oprettet i kalenderen");
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
            System.out.println("Indtast nr for ferie");
            int choice = Menu.inInt(Main.holidays.size())-1;



        String date1 = Main.holidays.get(choice)[0];
        String date2 = Main.holidays.get(choice)[1];


        int date11 = Main.dates.indexOf(date1);
        int date22 = Main.dates.indexOf(date2);
        int diff = date22 - date11;

        for (int i = 0; i <= diff; i++) {

            for (int j = 1; j <= 16; j++) {

                Main.calender.get(date11 + i)[j] = "0";


            }

        }

        ToFile.saveList(Main.calender, "calender.txt");


        Main.holidays.remove(choice);
        ToFile.saveList(Main.holidays, "holidays.txt");
            System.out.println("Din ferie er nu slettet fra kalenderen");
    }

}}




