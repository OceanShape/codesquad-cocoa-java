import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

enum GameStatus { GAME_START, RESOLVE, GAME_OVER, WIN, LOSE }

public class Hangman {

    private static Hangman singleton = new Hangman();

    private GameStatus status = GameStatus.GAME_START;

    private List<String> words;

    private String targetWord;

    private String solvedWord;

    private int life;

    public void readFile(){
        try {
            words = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "\\week2-study\\day1\\src" + "\\word.txt"));

            for (String s : words) {
                if (s.length() >= 46) {
                    throw new IllegalArgumentException("Error: word [" + s + "] is too long");
                }
                for (char c : s.toCharArray()) {
                    if (!Character.isAlphabetic(c)) {
                        throw new IllegalArgumentException("Error: word [" + s + "] is invalid");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectWord() {
        int idx = (int) (Math.random() * (words.size() - 1));
        targetWord = words.get(idx);
        solvedWord = "_".repeat(targetWord.length());
    }

    public char toUpperCustom(char ch) {
        if ((97 <= (int) ch) && ((int) ch <= 122)) {
            return (char)((int) ch - 32);
        }
        return ch;
    }

    // 1. 글자 존재 참/거짓 여부
    // 2. solvedWord 변경
    public boolean isSpellDetected(char ch){
        boolean isDetected = false;
        StringBuilder sb = new StringBuilder();
        char input = toUpperCustom(ch);
        char[] targetWordArray = targetWord.toCharArray();
        char[] targetWordUpperArray = targetWord.toUpperCase().toCharArray();

        for (int i = 0; i < targetWord.length(); ++i) {
            if (targetWordUpperArray[i] == input) {
                isDetected = true;
                sb.append(targetWordArray[i]);
            } else {
                if (solvedWord.charAt(i) != '_') {
                    sb.append(solvedWord.charAt(i));
                } else {
                    sb.append('_');
                }
            }
        }

        solvedWord = sb.toString();

        return isDetected;
    }

    public boolean isAllSpellSolved() {
        char[] solvedWordArray = solvedWord.toCharArray();

        for (char c : solvedWordArray) {
            if (c == '_') {
                return false;
            }
        }

        return true;
    }

    public void updateStatus(char ch) {

        if (!isSpellDetected(ch)) {
            --life;
        }

        if (life == 0 || isAllSpellSolved()) {
            if (life == 0) {
                status = GameStatus.LOSE;
            } else {
                status = GameStatus.WIN;
            }
            solvedWord =  "answer is " + targetWord;
        }
    }

    public void renderScreen() {
        if (status == GameStatus.RESOLVE) {
            System.out.println("============================================================");
            System.out.println("word: " + solvedWord);
            System.out.println("============================================================");
        } else if (status == GameStatus.GAME_START) {
            System.out.println("============================================================");
            System.out.println("====================PRESS ENTER TO START====================");
            System.out.println("============================================================");
        } else if (status == GameStatus.WIN) {
            System.out.println("============================================================");
            System.out.println("==========================YOU  WIN==========================");
            System.out.println("=======================CONTINUE(Y/N)?=======================");
            System.out.println("============================================================");
        } else if (status == GameStatus.LOSE) {
            System.out.println("============================================================");
            System.out.println("==========================YOU LOSE==========================");
            System.out.println("=======================CONTINUE(Y/N)?=======================");
            System.out.println("============================================================");
        } else if (status == GameStatus.GAME_OVER) {
            System.out.println("============================================================");
            System.out.println("=========================GAME  OVER=========================");
            System.out.println("============================================================");
        }
    }

    public void play() {
        try {
            readFile();

            // Game loop
            Scanner sc = new Scanner(System.in);
            while (true) {
                if (status == GameStatus.GAME_START) {
                    selectWord();
                    renderScreen();
                    sc.next();
                    status = GameStatus.RESOLVE;
                } else if (status == GameStatus.WIN || status == GameStatus.LOSE) {
                    renderScreen();
                    char input = sc.next().charAt(0);
                    boolean isGameOver = false;
                    while (true) {
                        if (input == 'Y' || input == 'y') {
                            isGameOver = true;
                            break;
                        } else if (input == 'N' || input == 'n') {
                            break;
                        }

                    }

                    if (isGameOver) {
                        status = GameStatus.GAME_OVER;
                        renderScreen();
                        break;
                    }
                }

                char input = sc.next().charAt(0);

                updateStatus(input);
                renderScreen();
            }

            sc.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private Hangman() { }

    public static Hangman getInstance() {
        return singleton;
    }

}
