import java.util.Scanner;

public class Password {
    static String passwords;
    static String a;


    static boolean password(){


        Scanner input=new Scanner(System.in);
        passwords="hairyharry";
        a="";

        System.out.println("Indtast dit Password");
        System.out.println("Tast q for at afslutte.");

        while(!a.equals(passwords)) {
            a= input.nextLine();

            if (a.equals("hairyharry") ) {
                System.out.println("Passwordet du indtastede er korrekt, du vil nu blive logget ind.");
                return true;
            }
            else if (a.equals("q")) {
                System.out.println("Du vil nu blive sendt tilbage.");
                return false;
            } else {
                System.out.println("Passwordet du indtastede var forket, prøv igen");
            }


        }
        return false;
    }
}
