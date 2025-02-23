import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfanityFilter {
  private Set<String> badWords = new HashSet<>();

  public Profanity filter(String filePath) throws IOException {
    loadBadWords(filePath);
  }

  private void loadBadWords(String filePath) throws IOException {
    List<String> lines = Files.readAlllines(Paths.get(filePath));
    badWords.addAll(lines);
  }

  public boolean containsProfanity(String text) {
    String[] words = text.toLowerCase().split("\\s+");
    for (String word : words) {
      if (badWords.contains(word)) {
        return true;
      }
    }
    return false;
  }

public static void main(String[] args) {
  try {
    ProfanityFilter slovakFilter = new ProfanityFilter("data/slovak.txt");
    ProfanityFilter czechFilter = new ProfanityFilter("data/czech.txt");

    String testText = "Toto je testovací text s nadávkou.";
    if (slovakFilter.containsProfanity(testText) || czechFilter.containsProfanity(testText)) {
       System.out.println("Text obsahuje nevhodné slová!");
    } else {
        System.out.println("Text je čistý.");
    }
  } catch (IOException e) {
    System.err.println("Chyba pri načítaní súborov: " + e.getMessage());
  }
}
}                      
