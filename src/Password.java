import java.util.Scanner;

public class Password {


    public static void main(String[] args) {
            Scanner input=new Scanner(System.in);
            String password="hairyharry";
            String a="";

            System.out.println("Indtast dit Password");
            System.out.println("Tast q for at afslutte.");

            while(!a.equals(password)) {
                a= input.nextLine();

                if (a.equals("hairyharry") ) {
                    System.out.println("Passwordet du indtastede er korrekt, du vil nu blive logget ind.");
                }
                else if (a.equals("q")) {
                    break;
                } else {
                    System.out.println("Passwordet du indtastede var forket, prøv igen");
                }


            }

        }
    }

