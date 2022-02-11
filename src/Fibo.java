import java.util.Scanner;

public class Fibo implements Command {
    @Override
    public String name() {
        return "fibo";
    }
    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Quel valeur de fibonnaci voulez-vous calculer ?");
        String cmd = scanner.nextLine();
        int n = 0;
        try {
            n = Integer.parseInt(cmd);
            int n1 = 0;
            int n2 = 1;
            for (int i = 0; i < n; i++) {
                int tmp = n2;
                n2 = n2 + n1;
                n1 = tmp;
            }
            System.out.println(n1);
        } catch(NumberFormatException e) {
            System.out.println("Tu n'as pas mis un nombre !");
        }
        return false;
    }
}
