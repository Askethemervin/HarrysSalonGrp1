import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static ArrayList<String[]> calender = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<String> phoneNumbers = new ArrayList<>();
    static ArrayList<String> dates = new ArrayList<>();


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
        for (String[] s: calender) {
            dates.add(s[0]);
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
            System.out.println();
            String[] items = new String[]{"Se ledige tider", "Se/Ændre kunders tider", "Se/slet tid på dato","Opret kunde", "Salg"};
            Menu.menu(items);

            switch (Menu.op) {
                case 1 -> {
                    System.out.println("Indtast dato (yyyy/MM/dd):");
                    date = input.nextLine();

                    if (dates.contains(date)) {

                        Available.available(date);
                        System.out.println("Vil du booke tid?");
                        Menu.menu(new String[]{"Ja", "Nej"});
                        if (Menu.op == 1) {
                            System.out.println("Indtast dag (dd):");
                            String dayStr = input.nextLine();
                            if (Arrays.asList(Available.dayOptions).contains(dayStr)) {
                                String[] dateArr = date.split("/");
                                if (Integer.parseInt(dayStr) < Integer.parseInt(dateArr[2])) {
                                    dateArr[1] = Integer.toString(Integer.parseInt(dateArr[1]) + 1);
                                }
                                dateArr[2] = dayStr;

                                date = String.join("/", dateArr);
                                Available.timeCheck(date);
                                System.out.println("Indtast tidspunkt (tt:mm):");
                                String timeStr = input.nextLine();

                                if (Available.timeOptions.contains(timeStr)) {

                                    System.out.println("Telefon nr:");
                                    int teleNr = input.nextInt();
                                    input.nextLine();
                                    if (teleNr > 9999999 && teleNr < 100000000) {
                                        String teleStr = Integer.toString(teleNr);
                                        Book.book(date, timeStr, teleStr);
                                    } else System.out.println("Ugyldigt telefon nr.");
                                } else {
                                    System.out.println("Ugyldigt tidspunkt valgt");
                                }
                            } else {
                                System.out.println("Ugyldig dag valgt");
                            }

                        }
                    }
                    else System.out.println("Ugyldig dato valgt");


                }
                case 2 -> {
                    System.out.println("Indtast telefon nr.;");
                    String tlfnr=input.nextLine();

                    if(!Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings.isEmpty()) {
                    System.out.println(Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).name+" har følgende reservationer:");
                    int j=1;
                    for (String[] s: Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings){
                        System.out.println(j+": "+Arrays.toString(s));
                        j++;

                    }

                        System.out.println("Kunne du tænke dig at ændre en tid?");
                        Menu.menu(new String[]{"Ja", "Nej"});
                        if (Menu.op == 1) {

                            System.out.println("Indtast nummeret for tiden");

                            ArrayList<String[]> cbookings = Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings;

                            int choice = Menu.inInt(cbookings.size());


                            System.out.println("Er du sikker på at du vil ændre tiden " + Arrays.toString(cbookings.get(choice - 1)) + "?");
                            Menu.menu(new String[]{"Ja", "Nej"});
                            if (Menu.op == 1) {
                                System.out.println("Tiden " + cbookings.get(choice - 1)[0] + " kl. " + cbookings.get(choice - 1)[1] + " er slettet. Vil du booke en ny?");
                                Book.delete(cbookings.get(choice - 1)[0], cbookings.get(choice - 1)[1]);


                                Menu.menu(new String[]{"Ja", "Nej"});
                                if (Menu.op == 1) {
                                    System.out.println("Indtast dato (yyyy/MM/dd):");
                                    date = input.nextLine();

                                    Available.available(date);


                                    System.out.println("Indtast dag (dd):");
                                    String dayStr = input.nextLine();
                                    String[] dateArr = date.split("/");
                                    dateArr[2] = dayStr;
                                    date = String.join("/", dateArr);

                                    System.out.println("Indtast tidspunkt (tt:mm):");
                                    String timeStr = input.nextLine();


                                    Book.book(date, timeStr, tlfnr);

                                }


                            }


                        }


                    }
                    else System.out.println(Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).name+" har ingen reservationer.");
                }
                case 3 -> {

                    System.out.println("Indtast dato (yyyy/MM/dd):");
                    date = input.nextLine();
                    if (dates.contains(date)) {
                        Available.reserved(date);
                        System.out.println("Vil du slette en tid?");
                        Menu.menu(new String[]{"Ja", "Nej"});
                        if (Menu.op == 1) {

                            System.out.println("Indtast tidspunkt (tt:mm):");
                            String timeStr = input.nextLine();

                            System.out.println("Er du sikker på at du vil slette denne tid " + timeStr + "?");
                            Menu.menu(new String[]{"Ja", "Nej"});
                            if (Menu.op == 1) {
                                Book.delete(date, timeStr);
                            }
                        }
                    }
                    else System.out.println("Ugyldig dato valgt.");
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
