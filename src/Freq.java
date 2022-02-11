import java.util.Scanner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readString;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Freq implements Command {
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Saisissez le nom du fichier que vous voulez analyser !");
        String pathString = scanner.nextLine();
        Path myPath = Paths.get(pathString);
        String content = null;
        try {
            content = readString(myPath);
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
            System.out.println(String.join(" ", myListFreq));
        } catch (IOException e) {
            System.out.println("Unreadable file: " + e);
        }
        return false;
    }
}
