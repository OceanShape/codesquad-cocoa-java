import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

enum GameStatus { GAME_START, RESOLVE, GAME_OVER, WIN }

public class Hangman {

    private static Hangman singleton = new Hangman();

    private GameStatus status = GameStatus.GAME_START;

    private List<String> words;

    public void readFile() throws IOException{
        words = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "\\week2-study\\day1\\src" + "\\word.txt"));
        for (String s : words) {
            if (s.length() >= 46) {
                throw new IOException("Error: word [" + s + "] is too long");
            }
            for (char c : s.toCharArray()) {
                if (!Character.isAlphabetic(c)) {
                    throw new IOException("Error: word [" + s + "] is invalid");
                }
            }
        }
    }

    public void play() {
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Hangman() { }

    public static Hangman getInstance() {
        return singleton;
    }

}
