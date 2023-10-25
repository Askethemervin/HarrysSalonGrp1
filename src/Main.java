import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<String[]> calender = new ArrayList<>();
    static String[] dates = new String[365];


    static Scanner input = new Scanner(System.in);
    static String date;


    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("calender.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                calender.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i=0; i<365; i++){
            dates[i]=(calender.get(i)[0]);
        }



        Book.book("2023/10/25", "10:00", "20632954");
        Book.book("2023/10/25", "10:30", "20632954");
        Book.book("2023/10/25", "11:00", "20632954");
        Book.book("2023/10/25", "11:30", "20632954");
        Book.book("2023/10/25", "13:00", "20632954");
        Book.book("2023/10/25", "14:30", "20632954");
        Book.book("2023/10/26", "10:00", "20632954");
        Available.available("2023/10/25");

        while (true) {
            String[] items = new String[]{"Se ledige tider", "Ændre tid", "Slet tid", "Salg"};
            Menu.menu(items);

            switch (Menu.op){
                case 1 ->{
                    System.out.println("Indtast dato (yyyy/MM/dd):");
                    date=input.next();

                    Available.available(date);
                    System.out.println("Vil du book tid?");
                    Menu.menu(new String[]{"Ja","Nej"});
                    if (Menu.op==1){
                        System.out.println("Indtast dag (dd):");
                        String dayStr=input.next();
                        String[] dateArr=date.split("/");
                        dateArr[2]=dayStr;
                        date=String.join("/",dateArr);

                        System.out.println("Indtast tidspunkt (tt:mm):");
                        String timeStr=input.next();

                        System.out.println("Telefon nr:");
                        int teleNr=input.nextInt();

                        if (teleNr>9999999 && teleNr<100000000){
                            String teleStr=Integer.toString(teleNr);
                            Book.book(date, timeStr, teleStr);
                        }
                        else System.out.println("Ugyldigt telefon nr.");

                    }

                }
            }
        }
    }
}