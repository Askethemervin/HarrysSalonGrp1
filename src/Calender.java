import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Calender {
    public static void main(String[] args) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        List<String[]> dataLines = new ArrayList<>();


        for (int i=0; i<365; i++){
//           if (c.get(Calendar.DAY_OF_WEEK)!=7 && c.get(Calendar.DAY_OF_WEEK)!=1 ) {
               switch (c.get(Calendar.DAY_OF_WEEK)) {
                   case 2 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Mandag"});
                   case 3 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Tirsdag"});
                   case 4 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Onsdag"});
                   case 5 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Torsdag"});
                   case 6 -> dataLines.add(new String[]{sdf.format(c.getTime()), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0","Fredag"});
               }
//           }
            c.add(Calendar.DATE, 1);

        }

        ToFile.saveList(dataLines);
    }


}

