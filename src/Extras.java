public class Extras {
    static int price = 0;

    static void buy() {

        boolean done = true;
        while (done) {
            Menu.menu(new String[]{"Shampoo, 100 kr.", "Voks, 50 kr.", "Balsam, 120 kr.", "Børste, 30 kr.", "Kam, 40 kr.", "Hårolie, 110 kr.", "Skægolie, 115 kr.", "Hårfarve: Sort, 150 kr. ", "Hårfarve: Blond, 160 kr.", "Hårfarve: Grøn, 300 kr."});
            switch (Menu.op) {
                case 1 -> price = price + 100;
                case 2 -> price = price + 50;
                case 3 -> price = price + 120;
                case 4 -> price = price + 30;
                case 5 -> price = price + 40;
                case 6 -> price = price + 110;
                case 7 -> price = price + 115;
                case 8 -> price = price + 150;
                case 9 -> price = price + 160;
                case 10 -> price = price + 300;

            }
            System.out.println("Vil du tilføje flere produkter?");
            Menu.menu(Main.janej);
            if (Menu.op == 2) done = false;

        }
    }
}
