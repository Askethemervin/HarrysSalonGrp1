import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Calender {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public static void main(String[] args) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        List<String[]> dataLines = new ArrayList<>();


        for (int i=0; i<365; i++){

               switch (c.get(Calendar.DAY_OF_WEEK)) {
                   case 2 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Mandag"});
                   case 3 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Tirsdag"});
                   case 4 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Onsdag"});
                   case 5 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Torsdag"});
                   case 6 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Fredag"});
               }

            c.add(Calendar.DATE, 1);

        }

        ToFile.saveList(dataLines,"calender.txt");
    }



    public static void addDays(ArrayList<String> dates) throws ParseException, IOException {
        String dateString = dates.get(dates.size()-1);
        Date date = sdf.parse(dateString);
        long diff = TimeUnit.DAYS.convert(date.getTime()-(new Date()).getTime(), TimeUnit.MILLISECONDS);
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        System.out.println(365-diff);
        for (int i=0; i<=365-diff; i++){
            d.add(Calendar.DATE, 1);
            switch (d.get(Calendar.DAY_OF_WEEK)) {
                case 2 -> Main.calender.add(new String[]{sdf.format(d.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Mandag"});
                case 3 -> Main.calender.add(new String[]{sdf.format(d.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Tirsdag"});
                case 4 -> Main.calender.add(new String[]{sdf.format(d.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Onsdag"});
                case 5 -> Main.calender.add(new String[]{sdf.format(d.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Torsdag"});
                case 6 -> Main.calender.add(new String[]{sdf.format(d.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Fredag"});
            }

            if (d.get(Calendar.DAY_OF_WEEK)==2 ||  d.get(Calendar.DAY_OF_WEEK)==3 ||  d.get(Calendar.DAY_OF_WEEK)==4 ||  d.get(Calendar.DAY_OF_WEEK)==5 ||  d.get(Calendar.DAY_OF_WEEK)==6){
                Main.dates.add(sdf.format(d.getTime()));
                Main.payments.add(new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"});
            }



        }

        ToFile.saveList(Main.calender,"calender.txt");
        ToFile.saveList(Main.payments, "PaymentCalender.txt");

    }
}

