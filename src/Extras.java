public class Extras {
    static int price = 0;

    static void buy() {



        boolean done = true;
        while (done) {
            Menu.menu(new String[]{"Shampoo", "Voks", "Balsam"});
            switch (Menu.op) {
                case 1 -> price = price + 100;
                case 2 -> price = price + 50;
                case 3 -> price = price + 120;

            }
            System.out.println("Vil du tilføje flere produkter?");
            Menu.menu(Main.janej);
            if (Menu.op == 2) done = false;


        }
    }
}
