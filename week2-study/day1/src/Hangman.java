enum GameStatus { GAME_START, RESOLVE, GAME_OVER, WIN }

public class Hangman {

    private static Hangman singleton = new Hangman();

    public void play() { }

    private Hangman() { }

    public static Hangman getInstance() {
        return singleton;
    }

}
