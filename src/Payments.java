public class Payments {
    static void searchByDate(String date) {
        if (Main.dates.contains(date)) {
            System.out.println("Kunder og reservationer for dato: " + date);
            boolean found = false;
            int totalPrice = 0;
            int dateIndex = Main.dates.indexOf(date);
            String telnr;
            int custIndex;

            for (int i = 1; i < 17; i++) {
                telnr = Main.calender.get(dateIndex)[i];
                if (!telnr.equals("0") && !telnr.equals("1")) {
                    custIndex = Main.phoneNumbers.indexOf(telnr);
                    System.out.println("Kunde: " + Main.customers.get(custIndex).name);
                    System.out.println("Telefon nr.: " + Main.customers.get(custIndex).tlfnr);
                    System.out.println("Tidspunkt: " + Available.timeindex(i));
                    if (Integer.parseInt(Book.payedPrice(date, Available.timeindex(i))) < 0) {
                        System.out.println("Aftale om kredit: " + Integer.parseInt(Book.payedPrice(date, Available.timeindex(i))) * -1);
                    } else {
                        System.out.println("Transaktion: " + Book.payedPrice(date, Available.timeindex(i)) + "\n");
                        totalPrice = totalPrice + Integer.parseInt(Book.payedPrice(date, Available.timeindex(i)));

                        found = true;
                    }
                }
            }

            System.out.println("\nIndkomst for denne dato er " + totalPrice);

            if (!found) {
                System.out.println("Ingen betalinger fundet for denne dato.");
            }
        }
        else System.out.println("Ugyldig dato");
    }
}
