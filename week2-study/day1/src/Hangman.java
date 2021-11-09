import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

enum GameStatus { GAME_START, RESOLVE, GAME_OVER, WIN }

public class Hangman {

    private static Hangman singleton = new Hangman();

    private GameStatus status = GameStatus.GAME_START;

    private List<String> words;

    private String targetWord;

    private String solvedWord;

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

    public void selectWord() {
        int idx = (int) (Math.random() * (words.size() - 1));
        targetWord = words.get(idx);
        solvedWord = "_".repeat(targetWord.length());
    }

    public void updateStatus(char input) { }

    public void renderScreen() { }

    public void play() {
        try {
            readFile();
            // Game loop
            while (true) {
                if (status == GameStatus.GAME_START) {
                    selectWord();
                    renderScreen();
                    status = GameStatus.RESOLVE;
                } else if (status == GameStatus.GAME_OVER) {
                    break;
                }

                Scanner sc = new Scanner(System.in);
                char input = sc.next().charAt(0);

                updateStatus(input);
                renderScreen();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Hangman() { }

    public static Hangman getInstance() {
        return singleton;
    }

}
