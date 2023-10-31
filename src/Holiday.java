import java.io.IOException;
import java.util.Scanner;

public class Holiday {


    static void holiday() throws IOException {

        String date1 = "";
        String date2 = "";


        Scanner input1 = new Scanner(System.in);

        Scanner input2 = new Scanner(System.in);

        boolean korrektinput = true;

        System.out.println("Skriv en dato");

        while(korrektinput) {

            date1 = input1.nextLine();
            if (Main.dates.contains(date1)) {

                korrektinput = false;

            } else System.out.println("Du har indtastet en utilgængelig dato");


        }
        System.out.println("Skriv den næste dato");

        korrektinput = true;

        while(korrektinput) {

            date2 = input2.nextLine();

            if (Main.dates.contains(date2)) {

                korrektinput = false;
            } else System.out.println("Du har indtastet en utilgængelig dato");

        }

        int date11 = Main.dates.indexOf(date1);
        int date22 = Main.dates.indexOf(date2);
        int diff = date22-date11;

        for(int i = 0; i <= diff; i++) {

            for(int j = 1; j <= 16; j++) {

                Main.calender.get(date11+i)[j] = "1";


            }

        }

        ToFile.saveList(Main.calender, "calender.txt");
    }

    public static void main(String[] args) {



    }


}
