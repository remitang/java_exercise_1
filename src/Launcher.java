import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readString;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Bienvenue ! Choisissez une commande entre 'fibo', 'freq', 'predict' et 'quit' !");
        Scanner scanner = new Scanner(System.in);
        Quit quit = new Quit();
        Fibo fibo = new Fibo();
        Freq freq = new Freq();
        Predict predict = new Predict();
        List<Command> allCmd = new ArrayList<>();
        allCmd.add(quit);
        allCmd.add(fibo);
        allCmd.add(freq);
        allCmd.add(predict);
        boolean finish = false;
        boolean unknownCmd = false;
        do {
            String myCmd = scanner.nextLine();
            unknownCmd = false;
            for (Command command : allCmd)
            {
                if (myCmd.equals(command.name())) {
                    finish = command.run(scanner);
                    unknownCmd = true;
                    break;
                }
            }
            if (!unknownCmd) {
                System.out.println("Unknown command");
            }
        } while (!finish);
    }
}