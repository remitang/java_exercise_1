import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readString;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Predict implements Command {
    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Saisissez le nom du fichier que vous voulez analyser !");
        String pathString = scanner.nextLine();
        Path myPath = Paths.get(pathString);
        String content = null;
        try {
            content = readString(myPath);
            System.out.println("Quel mot ?");
            String userWord = scanner.nextLine().toLowerCase(Locale.ROOT);
            content = content.toLowerCase()
                    .replaceAll("[.!?,(\\r\\n|\\r|\\n)]", " ")
                    .replaceAll(" {2}", " ");
            if (!content.contains(userWord)) {
                System.out.println("Le mot n'est pas présent dans le texte !");
                return false;
            }
            String[] words = content.split(" ");
            Map<String, String> wordsFreq = new HashMap<String, String>();
            for (String w : words){
                if (wordsFreq.get(w) == null) {
                    wordsFreq.put(w, getNextFreq(w, words));
                }
            }
            System.out.print(userWord);
            for(int i = 0; i < 19 ; i++){
                System.out.print(" " + wordsFreq.get(userWord));
                userWord = wordsFreq.get(userWord);
            }
        } catch (IOException e) {
            System.out.println("Unreadable file: " + e);
        }
        System.out.println("");
        return false;
    }

    private String getNextFreq(String w, String[] words) {
        ArrayList<String> tmp = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(w) && i != words.length - 1) {
                tmp.add(words[i + 1]);
            }
        }
        Map<Object, Integer> frequencyMap = tmp.stream()
                .collect(toMap(
                        s -> s,
                        s -> 1,
                        Integer::sum));
        List<Object> res = tmp.stream()
                .sorted(comparing(frequencyMap::get).reversed())
                .distinct()
                .limit(1)
                .collect(toList());
        if (res.size() != 0) {
            return res.get(0).toString();
        }
        return "";
    }
}
