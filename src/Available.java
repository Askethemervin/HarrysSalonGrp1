import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    static void available(String date) throws ParseException {
        String dateString1 = Main.dates.get(0);
        String dateString2 = Main.dates.get(Main.dates.size() - 1);
        Date date1 = sdf.parse(dateString1);
        Date date2 = sdf.parse(dateString2);
        Date dateD = sdf.parse(date);


        if (Main.dates.contains(date)) {
            daynr = Main.dates.indexOf(date);
            cont = true;
        } else if (dateD.getTime() > date1.getTime() && (dateD.getTime() < date2.getTime())) {
            Calendar d = Calendar.getInstance();
            d.setTime(dateD);
            d.add(Calendar.DATE, 1);
            if (Main.dates.contains(sdf.format(d.getTime()))) {
                daynr = Main.dates.indexOf(sdf.format(d.getTime()));
                cont = true;
            } else {
                d.add(Calendar.DATE, 1);
                daynr = Main.dates.indexOf(sdf.format(d.getTime()));
                cont = true;
            }
        } else {
            System.out.println("Ugyldig dato valgt");
            cont = false;
        }

        if (cont) {


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
                System.out.println();
                daynr++;
                System.out.println();
            }

        }
    }


    static void reserved(String date){

        daynr=Main.dates.indexOf(date);

            day =Main.calender.get(daynr);
            dayName=day[17];

            System.out.println(dayName+", "+date + " er følgende tider reserverede: ");
            for (int i = 1; i < 17; i++) {
                if (!day[i].equals("0")) {
                    System.out.println(timeindex((i))+", Tlf: "+day[i]);

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
