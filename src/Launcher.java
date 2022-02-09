import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("That's a text from Launcher!");
        Scanner scanner = new Scanner(System.in);
        String my_txt = scanner.nextLine();
        while (!my_txt.equals("quit")) {
            System.out.println("Unknown command");
            my_txt = scanner.nextLine();
        }
    }
}