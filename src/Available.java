import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Available {
    static int daynr;
    static String[] day = new String[17];
    static String dayName;
    static String[] dayOptions = new String[5];
    static ArrayList<String> timeOptions;
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    static boolean cont;
    static boolean contx;

    static String timeindex(int t) {

        switch (t) {
            case 1 -> { return "10:00"; }

            case 2 -> { return "10:30"; }

            case 3 -> { return "11:00"; }

            case 4 -> { return "11:30"; }

            case 5 -> { return "12:00"; }

            case 6 -> { return "12:30"; }

            case 7 -> { return "13:00"; }

            case 8 -> { return "13:30"; }

            case 9 -> { return "14:00"; }

            case 10 -> { return "14:30"; }

            case 11 -> { return "15:00"; }

            case 12 -> { return "15:30"; }

            case 13 -> { return "16:00"; }

            case 14 -> { return "16:30"; }

            case 15 -> { return "17:00"; }

            case 16 -> { return "17:30"; }
        }

        return "";
    }

    static void available(String nr, boolean ask) throws ParseException, IOException {
        cont = true;
        contx = true;
        String date;
        String dateOg = null;
        String dateString1 = Main.dates.get(0);
        String dateString2 = Main.dates.get(Main.dates.size() - 1);
        Date date1 = sdf.parse(dateString1);
        Date date2 = sdf.parse(dateString2);

        while (contx) {
            cont=true;
            while (cont) {

                System.out.println("Indtast dato (yyyy/MM/dd):");
                date = Main.input.nextLine();
                dateOg = date;
                try {
                    Date dateD = sdf.parse(date);


                    if (Main.dates.contains(date)) {
                        daynr = Main.dates.indexOf(date);
                        cont = false;
                    } else if (dateD.getTime() > date1.getTime() && (dateD.getTime() < date2.getTime())) {
                        Calendar d = Calendar.getInstance();
                        d.setTime(dateD);
                        d.add(Calendar.DATE, 1);
                        if (!Main.dates.contains(sdf.format(d.getTime()))) {
                            d.add(Calendar.DATE, 1);
                        }
                        daynr = Main.dates.indexOf(sdf.format(d.getTime()));
                        cont = false;
                    } else {
                        System.out.println("Ugyldig dato valgt");

                    }
                } catch (Exception e) {
                    System.out.println("Ugyldigt input");

                }
            }

            for (int j = 0; j < 5; j++) {
                day = Main.calender.get(daynr);
                date = Main.dates.get(daynr);
                dayOptions[j] = date.split("/")[2];
                dayName = day[17];

                System.out.println(dayName + ", " + date + " er følgende tider ledige: ");
                for (int i = 1; i < 17; i++) {
                    if (day[i].equals("0")) {
                        System.out.print(timeindex(i) + ", ");

                    }
                }
                daynr++;
                System.out.println("\n");
            }
            if (ask) {
                System.out.println("Vil du booke tid?");
                Menu.menu(Main.janej);
                contx=false;
            } else Menu.op = 1;
            if (Menu.op == 1) {
                cont = true;
                while (cont) {

                    System.out.println("Indtast dag (dd):");
                    String dayStr = Main.input.nextLine();

                    if (Arrays.asList(dayOptions).contains(dayStr)) {
                        String[] dateArr = dateOg.split("/");
                        if (Integer.parseInt(dayStr) < Integer.parseInt(dateArr[2])) {
                            dateArr[1] = Integer.toString(Integer.parseInt(dateArr[1]) + 1);
                        }
                        dateArr[2] = dayStr;

                        date = String.join("/", dateArr);
                        Available.timeCheck(date);
                        if (timeOptions.isEmpty()) {
                            System.out.println("Ingen ledige tider på den valgte dag");
                            cont =false;
                        }
                        while (cont) {
                            System.out.println("Indtast tidspunkt (tt:mm):");
                            String timeStr = Main.input.nextLine();

                            if (Available.timeOptions.contains(timeStr)) {
                                if (nr.isEmpty()) {
                                    nr = Menu.inTlf();
                                }

                                Book.book(date, timeStr, nr);
                                System.out.println("Tiden er nu booket");
                                cont = false;
                                contx = false;

                            } else {
                                System.out.println("Ugyldigt tidspunkt valgt");
                            }
                        }

                    } else {
                        System.out.println("Ugyldig dag valgt");
                    }

                }
            }
        }
    }

    static void reserved(String date){
        timeOptions = new ArrayList<>();
        daynr=Main.dates.indexOf(date);

        day =Main.calender.get(daynr);
        dayName=day[17];

        System.out.println(dayName+", "+date + " er følgende tider reserverede: ");
        for (int i = 1; i < 17; i++) {
            if (!day[i].equals("0")) {
                System.out.println(timeindex((i))+", Tlf: "+day[i]);
                timeOptions.add(timeindex((i)));
            }
        }
        System.out.println();
    }

    static void timeCheck(String date){
        timeOptions = new ArrayList<>();

        daynr=Main.dates.indexOf(date);

        day =Main.calender.get(daynr);

        for (int i = 1; i < 17; i++) {
            if (day[i].equals("0")) {
                timeOptions.add(timeindex(i));

            }
        }
    }
}
