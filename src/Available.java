import java.util.ArrayList;

public class Available {
    static int daynr;
    static String[] day = new String[17];
    static String dayName;
    static String[] dayOptions = new String[5];
    static ArrayList<String> timeOptions;

    static void available(String date){

        daynr=Main.dates.indexOf(date);


        for (int j=0; j<5; j++) {
            day =Main.calender.get(daynr);
            dayOptions[j]=date.split("/")[2];
            dayName=day[17];

            System.out.println(dayName+", "+date + " er følgende tider ledige: ");
            for (int i = 1; i < 17; i++) {
                if (day[i].equals("0")) {
                    switch (i) {
                        case 1 -> System.out.print("10:00, ");

                        case 2 -> System.out.print("10:30, ");

                        case 3 -> System.out.print("11:00, ");

                        case 4 -> System.out.print("11:30, ");

                        case 5 -> System.out.print("12:00, ");

                        case 6 -> System.out.print("12:30, ");

                        case 7 -> System.out.print("13:00, ");

                        case 8 -> System.out.print("13:30, ");

                        case 9 -> System.out.print("14:00, ");

                        case 10 -> System.out.print("14:30, ");

                        case 11 -> System.out.print("15:00, ");

                        case 12 -> System.out.print("15:30, ");

                        case 13 -> System.out.print("16:00, ");

                        case 14 -> System.out.print("16:30, ");

                        case 15 -> System.out.print("17:00, ");

                        case 16 -> System.out.print("17:30 ");
                    }


                }

            }
            System.out.println();
            daynr++;
            date=Main.dates.get(daynr);
            System.out.println();
        }


    }
    static void reserved(String date){

        daynr=Main.dates.indexOf(date);


            day =Main.calender.get(daynr);
            dayName=day[17];

            System.out.println(dayName+", "+date + " er følgende tider reserverede: ");
            for (int i = 1; i < 17; i++) {
                if (!day[i].equals("0")) {
                    switch (i) {
                        case 1 -> System.out.print("10:00");

                        case 2 -> System.out.print("10:30");

                        case 3 -> System.out.print("11:00");

                        case 4 -> System.out.print("11:30");

                        case 5 -> System.out.print("12:00");

                        case 6 -> System.out.print("12:30");

                        case 7 -> System.out.print("13:00");

                        case 8 -> System.out.print("13:30");

                        case 9 -> System.out.print("14:00");

                        case 10 -> System.out.print("14:30");

                        case 11 -> System.out.print("15:00");

                        case 12 -> System.out.print("15:30");

                        case 13 -> System.out.print("16:00");

                        case 14 -> System.out.print("16:30");

                        case 15 -> System.out.print("17:00");

                        case 16 -> System.out.print("17:30");
                    }

                    System.out.println(", Tlf: "+day[i]);
                }




        }
        System.out.println();

    }

    static void timeCheck(String date){
        timeOptions = new ArrayList<String>();

        daynr=Main.dates.indexOf(date);

        day =Main.calender.get(daynr);

        for (int i = 1; i < 17; i++) {
            if (day[i].equals("0")) {
                switch (i) {
                    case 1 -> timeOptions.add("10:00");

                    case 2 -> timeOptions.add("10:30");

                    case 3 -> timeOptions.add("11:00");

                    case 4 -> timeOptions.add("11:30");

                    case 5 -> timeOptions.add("12:00");

                    case 6 -> timeOptions.add("12:30");

                    case 7 -> timeOptions.add("13:00");

                    case 8 -> timeOptions.add("13:30");

                    case 9 -> timeOptions.add("14:00");

                    case 10 -> timeOptions.add("14:30");

                    case 11 -> timeOptions.add("15:00");

                    case 12 -> timeOptions.add("15:30");

                    case 13 -> timeOptions.add("16:00");

                    case 14 -> timeOptions.add("16:30");

                    case 15 -> timeOptions.add("17:00");

                    case 16 -> timeOptions.add("17:30");
                }


            }

        }


    }


}
