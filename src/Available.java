import java.util.Arrays;

public class Available {
    static int daynr;
    static String[] day = new String[17];
    static String dayName;

    static void available(String date){

        daynr=Main.dates.indexOf(date);


        for (int j=0; j<5; j++) {
            day =Main.calender.get(daynr);
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
            System.out.println("\n");
            daynr++;
            date=Main.dates.get(daynr);

        }
        System.out.println("\n");
    }
    static void reserved(String date){

        daynr=Main.dates.indexOf(date);


            day =Main.calender.get(daynr);
            dayName=day[17];

            System.out.println(dayName+", "+date + " er følgende tider reserverede: ");
            for (int i = 1; i < 17; i++) {
                if (!day[i].equals("0")) {
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
        System.out.println("\n");
    }




}
