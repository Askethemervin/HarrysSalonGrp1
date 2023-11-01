import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static ArrayList<String[]> calender = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<String> phoneNumbers = new ArrayList<>();
    static ArrayList<String> dates = new ArrayList<>();
    static ArrayList<String[]> payments = new ArrayList<>();
    static ArrayList<String[]> holidays = new ArrayList<>();

    static Scanner input = new Scanner(System.in);
    static String date;
    static String[] janej = new String[]{"Ja","Nej"};

    
    public static void main(String[] args) throws IOException, ParseException {
        boolean john;

        try (BufferedReader br = new BufferedReader(new FileReader("calender.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                calender.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(new FileReader("holidays.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                holidays.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(new FileReader("PaymentCalender.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                payments.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String[] s: calender) {
            dates.add(s[0]);
        }

        Calender.addDays(dates);




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



        boolean cont = true;



        while (cont) {
            System.out.println();
            String[] items = new String[]{"Se ledige tider", "Se/Ændre kunders tider", "Se/slet tid på dato","Opret kunde", "Betal","Se betalinger på dato","Feriedage","Luk program"};
            Menu.menu(items);

            switch (Menu.op) {
                case 1 -> {
                    System.out.println("Indtast dato (yyyy/MM/dd):");
                    date = input.nextLine();



                    Available.available(date);
                    System.out.println("Vil du booke tid?");
                    Menu.menu(janej);
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
                case 2 -> {
                    System.out.println("Indtast telefon nr.;");
                    String tlfnr=input.nextLine();
                    if (phoneNumbers.contains(tlfnr)) {

                        if (!Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings.isEmpty()) {
                            System.out.println(Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).name + " har følgende reservationer:");
                            int j = 1;
                            for (String[] s : Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings) {
                                System.out.println(j + ": " + Arrays.toString(s));
                                j++;

                            }

                            System.out.println("Kunne du tænke dig at flytte en reservation?");
                            Menu.menu(janej);
                            if (Menu.op == 1) {

                                System.out.println("Indtast nummeret for tiden");

                                ArrayList<String[]> cbookings = Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings;

                                int choice = Menu.inInt(cbookings.size());


                                System.out.println("Er du sikker på at du vil ændre tiden " + Arrays.toString(cbookings.get(choice - 1)) + "?");
                                Menu.menu(janej);
                                if (Menu.op == 1) {
                                    System.out.println("Tiden " + cbookings.get(choice - 1)[0] + " kl. " + cbookings.get(choice - 1)[1] + " er nu fjernet, vil du stadig ændre tiden til en anden?");
                                    Book.delete(cbookings.get(choice - 1)[0], cbookings.get(choice - 1)[1]);


                                    Menu.menu(janej);
                                    if (Menu.op == 1) {
                                        john=true;
                                        while(john) {
                                            System.out.println("Indtast dato (yyyy/MM/dd):");
                                            date = input.nextLine();
                                            if (dates.contains(date)) {
                                                while(john) {
                                                    Available.available(date);


                                                    System.out.println("Indtast dag (dd):");
                                                    String dayStr = input.nextLine();
                                                    if (Arrays.asList(Available.dayOptions).contains(dayStr)) {
                                                        while(john) {
                                                            String[] dateArr = date.split("/");
                                                            dateArr[2] = dayStr;
                                                            date = String.join("/", dateArr);

                                                            System.out.println("Indtast tidspunkt (tt:mm):");
                                                            String timeStr = input.nextLine();
                                                            if (Available.timeOptions.contains(timeStr)) {

                                                                Book.book(date, timeStr, tlfnr);
                                                                john=false;
                                                            } else
                                                                System.out.println("Ugyldig tidspunkt valgt");
                                                        }
                                                    } else
                                                        System.out.println("Ugyldig dag valgt");
                                                }
                                            } else
                                                System.out.println("Ugyldig dato valgt");
                                        }


                                    }


                                }


                            }


                        } else
                            System.out.println(Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).name + " har ingen reservationer.");
                    }
                    else System.out.println("Kunde eksisterer ikke");
                }
                case 3 -> {

                    System.out.println("Indtast dato (yyyy/MM/dd):");
                    date = input.nextLine();
                    if (dates.contains(date)) {
                        Available.reserved(date);
                        System.out.println("Vil du slette en tid?");
                        Menu.menu(janej);
                        if (Menu.op == 1) {

                            System.out.println("Indtast tidspunkt (tt:mm):");
                            String timeStr = input.nextLine();

                            System.out.println("Er du sikker på at du vil slette denne tid " + timeStr + "?");
                            Menu.menu(janej);
                            if (Menu.op == 1) {
                                Book.delete(date, timeStr);
                            }
                        }
                    }
                    else System.out.println("Ugyldig dato valgt.");
                }
                case 4 -> {
                    System.out.println("Indtast telefon nr.:");
                    int nr = Menu.inInt(99999999);

                    input.nextLine();
                    if (nr > 9999999) {
                        if (!phoneNumbers.contains(Integer.toString(nr))) {
                            System.out.println("Indtast navn:");
                            String name = input.nextLine();

                            customers.add(new Customer(Integer.toString(nr), name, new ArrayList<>()));

                            ToFile.saveCustomer(customers);
                        }
                        else System.out.println("Kunden er allerede oprettet.");
                    } else System.out.println("Ugyldigt telefon nr.");


                }
                case 5 -> {
                    System.out.println("Indtast telefon nr.;");
                    String tlfnr=input.nextLine();
                    if (phoneNumbers.contains(tlfnr)) {
                        if (!Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings.isEmpty()) {
                            System.out.println(Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).name + " har følgende reservationer:");
                            int j = 1;
                            for (String[] s : Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings) {
                                System.out.print(j + ": " + Arrays.toString(s));
                                if (Book.isPayed(s[0], s[1]) == 1) {
                                    System.out.println(", betalt");

                                } else if ((Book.isPayed(s[0], s[1]) == 0)) {
                                    System.out.println(", ikke betalt");
                                } else {
                                    System.out.println(", kredit givet");
                                }
                                j++;

                            }

                            System.out.println("Kunne du tænke dig at betale for en tid?");
                            Menu.menu(janej);
                            if (Menu.op == 1) {

                                System.out.println("Indtast nummeret for tiden");

                                ArrayList<String[]> cbookings = Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).bookings;

                                int choiceInt = Menu.inInt(cbookings.size());

                                String dateString = cbookings.get(choiceInt - 1)[0];
                                String timeString = cbookings.get(choiceInt - 1)[1];

                                if (Book.isPayed(dateString, timeString) == 1) {
                                    System.out.println("Der er betalt for tiden. Vil du tilføje ekstra?");
                                    Extras.price = Integer.parseInt(Book.payedPrice(dateString, timeString));
                                    Menu.menu(janej);
                                    if (Menu.op == 1) {
                                        Extras.buy();

                                        Book.pay(dateString, timeString, Integer.toString(Extras.price));
                                    }
                                } else if (Book.isPayed(dateString, timeString) == -1) {
                                    System.out.println("Indestående kredit " + Book.debt + " kr. betales.");
                                    Book.pay(dateString, timeString, Book.debt);

                                } else {
                                    System.out.println("Vil du tilføje ekstra?");
                                    Extras.price = 200;
                                    Menu.menu(janej);
                                    if (Menu.op == 1) {
                                        Extras.buy();

                                        System.out.println("Den samlede pris er " + Extras.price + " kr.");
                                    }
                                    System.out.println("Kontant betaling, eller kredit?");
                                    Menu.menu(new String[]{"Kontant", "Kredit"});
                                    switch (Menu.op) {
                                        case 1 -> Book.pay(dateString, timeString, Integer.toString(Extras.price));
                                        case 2 -> Book.pay(dateString, timeString, Integer.toString(-Extras.price));
                                    }

                                }


                            }


                        } else
                            System.out.println(Main.customers.get(Main.phoneNumbers.indexOf(tlfnr)).name + " har ingen reservationer.");
                    }
                    else System.out.println("Telefon nr. findes ikke.");
                }
                case 6 -> {
                    if(Password.password()){
                        System.out.println("Indtast dato (yyyy/MM/dd):");
                        date = input.nextLine();
                        Payments.searchByDate(date);


                    }

                }
                case 7 -> {
                    System.out.println("Feriedage");

                    Menu.menu(new String[]{"Book ferie", "Se/Slet ferie"});
                    if(Menu.op==1){
                        Holiday.holiday();
                    } else Holiday.changeHoliday();



                }



                case 8 -> {
                    System.out.println("Programmet lukkes");
                    cont=false;
                }


                }
            }
        }



}
