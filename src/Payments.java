public class Payments {
    static void searchByDate(String date) {
        System.out.println("Kunder og reservationer for dato: " + date);
        boolean found = false;
        int totalPrice = 0;

        for (Customer customer : Main.customers) {
            for (String[] booking : customer.bookings) {
                if (booking[0].equals(date)) {
                    System.out.println("Kunde: " + customer.name);
                    System.out.println("Telefon nr.: " + customer.tlfnr);
                    System.out.println("Tidspunkt: " + booking[1]);
                    if (Integer.parseInt(Book.payedPrice(date,booking[1]))<0){
                        System.out.println("Aftale om kredit: "+Integer.parseInt(Book.payedPrice(date,booking[1]))*-1);
                    }
                    else {System.out.println("Transaktion: " + Book.payedPrice(date, booking[1])+"\n");
                    }

                    totalPrice = totalPrice+Integer.parseInt(Book.payedPrice(date, booking[1]));

                    found = true;
                }
            }
        }

        System.out.println("Indkomst for denne dato er "+totalPrice);

        if (!found) {
            System.out.println("Ingen reservationer fundet for denne dato.");
        }
    }
}
