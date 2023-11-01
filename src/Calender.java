import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Calender {

    public static void addDays(ArrayList<String> dates) throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        long diff;
        Calendar d = Calendar.getInstance();
        if (dates.isEmpty()){
            d.setTime(new Date());
            d.add(Calendar.DATE, -1);
            diff=0;
        }
        else {
            String dateString = dates.get(dates.size() - 1);
            Date date = sdf.parse(dateString);
            diff = TimeUnit.DAYS.convert(date.getTime() - (new Date()).getTime(), TimeUnit.MILLISECONDS);
            d.setTime(date);
        }
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

