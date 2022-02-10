import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readString;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Launcher {
    private static int fibo(int n) {
        int n1 = 0;
        int n2 = 1;
        for (int i = 0; i < n; i++) {
            int tmp = n2;
            n2 = n2 + n1;
            n1 = tmp;
        }
        return n1;
    }

    private static String freq(String content) {
        content = content.toLowerCase(Locale.ROOT);
        String[] words = content.split(" ");
        List<String> list = Arrays.asList(words);
        Map<Object, Integer> frequencyMap = list.stream()
                .collect(toMap(
                        s -> s,
                        s -> 1,
                        Integer::sum));
        List<String> myListFreq = list.stream()
                .sorted(comparing(frequencyMap::get).reversed())
                .distinct()
                .limit(3)
                .collect(toList());
        return String.join(", ", myListFreq);
    }

    public static void main(String[] args) {
        System.out.println("Bienvenue ! Choisissez une commande entre 'fibo', 'freq' et 'quit' !");
        Scanner scanner = new Scanner(System.in);
        String myCmd = scanner.nextLine();
        while (!myCmd.equals("quit")) {
            if (myCmd.equals("fibo")) {
                System.out.println("Donnez un nombre pour que je vous calcule son numero Fibonnaci !");
                String n = scanner.nextLine();
                int num = 0;
                try {
                    num = Integer.parseInt(n);
                    System.out.println(fibo(num));
                } catch(NumberFormatException e) {
                    System.out.println("Tu n'as pas mis un nombre !");
                }
            }
            else if (myCmd.equals("freq")) {
                System.out.println("Saisissez le nom du fichier que  vous voulez analyser !");
                String pathString = scanner.nextLine();
                Path myPath = Paths.get(pathString);
                String content = null;
                try {
                    content = readString(myPath);
                    System.out.println(freq(content));
                } catch (IOException e) {
                    System.out.println("Unreadable file: " + e);
                }

            }
            else {
                System.out.println("Unknown command");
            }
            myCmd = scanner.nextLine();
        }
    }
}