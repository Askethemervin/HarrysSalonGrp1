import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<String[]> calender = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<String> phoneNumbers = new ArrayList<>();
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
        for (int i = 0; i < 365; i++) {
            dates[i] = (calender.get(i)[0]);
        }
        try (BufferedReader cr = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = cr.readLine()) != null) {
                String[] data = line.split(",");
                ArrayList<String[]> bookingsList = new ArrayList<>();
                if (data.length==3) {
                    String[] bookingsStr = data[2].split(";");

                    for (String s : bookingsStr) {
                        bookingsList.add(s.split("\\."));

                    }
                }
                customers.add(new Customer(data[0], data[1], bookingsList));

            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }




        while (true) {
            String[] items = new String[]{"Se ledige tider", "Se/Ændre kunders tider", "Slet tid","Opret kunde", "Salg"};
            Menu.menu(items);

            switch (Menu.op) {
                case 1 -> {
                    System.out.println("Indtast dato (yyyy/MM/dd):");
                    date = input.nextLine();

                    Available.available(date);
                    System.out.println("Vil du booke tid?");
                    Menu.menu(new String[]{"Ja", "Nej"});
                    if (Menu.op == 1) {
                        System.out.println("Indtast dag (dd):");
                        String dayStr = input.nextLine();
                        String[] dateArr = date.split("/");
                        dateArr[2] = dayStr;
                        date = String.join("/", dateArr);

                        System.out.println("Indtast tidspunkt (tt:mm):");
                        String timeStr = input.nextLine();

                        System.out.println("Telefon nr:");
                        int teleNr = input.nextInt();
                        input.nextLine();
                        if (teleNr > 9999999 && teleNr < 100000000) {
                            String teleStr = Integer.toString(teleNr);
                            Book.book(date, timeStr, teleStr);
                        } else System.out.println("Ugyldigt telefon nr.");

                    }

                }
                case 2 -> {
                    System.out.println("Indtast telefon nr.;");
                    String tlfnr=input.nextLine();

                    System.out.println(Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).name+" har følgende reservationer:");
                    int j=1;
                    for (String[] s: Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings){
                        System.out.println(j+": "+Arrays.toString(s));
                        j++;

                    }
                    System.out.println("Kunne du tænke dig at ændre en tid?");
                    Menu.menu(new String[] {"Ja", "Nej"});
                    if(Menu.op == 1) {

                        System.out.println("Indtast nummeret for tiden");

                        ArrayList<String[]> cbookings = Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings ;

                        int choice = Menu.inInt(cbookings.size());


                        System.out.println("Er du sikker på at du vil ændre tiden "+Arrays.toString(cbookings.get(choice-1))+"?");
                        Menu.menu(new String[] {"Ja","Nej"});
                        if (Menu.op==1) {
                            Book.book(cbookings.get(choice - 1)[0], cbookings.get(choice - 1)[1], "0");
                            System.out.println("Din tid er nu fjernet, du skal nu tilføje en ny.");
                            Available.available(cbookings.get(choice - 1)[0]);
                            System.out.println("Hvilken tid vil du ændre til?");
                            System.out.println("Indtast dato (yyyy/MM/dd):");
                            date = input.nextLine();

                            Available.available(date);
                            System.out.println("Vil du booke en ny tid?");
                            Menu.menu(new String[]{"Ja", "Nej"});
                            if (Menu.op == 1) {
                                System.out.println("Indtast dag (dd):");
                                String dayStr = input.nextLine();
                                String[] dateArr = date.split("/");
                                dateArr[2] = dayStr;
                                date = String.join("/", dateArr);

                                System.out.println("Indtast tidspunkt (tt:mm):");
                                String timeStr = input.nextLine();

                                Book.book(date, timeStr, tlfnr);
                                System.out.println("Din tid er nu ændret til "+date+" klokken " +timeStr);
                            }


                        }


                    }



                }
                case 3 -> {

                    System.out.println("Indtast dato (yyyy/MM/dd):");
                    date = input.nextLine();
                    Available.reserved(date);
                    System.out.println("Vil du slette en tid?");
                    Menu.menu(new String[]{"Ja", "Nej"});
                    if (Menu.op == 1) {

                        System.out.println("Indtast tidspunkt (tt:mm):");
                        String timeStr = input.nextLine();

                        System.out.println("Er du sikker på at du vil slette denne tid " + timeStr + "?");
                        Menu.menu(new String[]{"Ja", "Nej"});
                        if (Menu.op == 1) {
                            Book.book(date, timeStr, "0");
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Indtast telefon nr.:");
                    String nr = input.nextLine();
                    System.out.println("Indtast navn:");
                    String name = input.nextLine();

                    customers.add(new Customer(nr,name,new ArrayList<>()));

                    ToFile.saveCustomer(customers);


                }
            }
        }
    }
}
