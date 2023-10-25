import java.io.IOException;
import java.util.Arrays;

public class Book {
//    static List<String[]> records = new ArrayList<>();
//    static String[] dage = new String[17];
//
    public static void main(String[] args) throws IOException {

//        try (BufferedReader br = new BufferedReader(new FileReader("csv"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                records.add(values);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }



    static void book(String date, int time, String telnr) throws IOException {




        Main.calender.get(Arrays.asList(Main.dates).indexOf(date))[time]=telnr;

        ToFile.saveList(Main.calender);




    }






}
