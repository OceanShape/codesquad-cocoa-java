import java.util.Scanner;

enum ShellStatus {
    RUN,
    TERMINATE
}

public class Shell {

    // 어디가 문제인지 확인
    public String defaultDirectory;

    public String currentDirectory;

    public ShellStatus status = ShellStatus.RUN;

    public void printPrompt() {
    }

    public Shell() {
//        this.defaultDirectory = new String(System.getProperty("user.dir" + "\\week3-study\\src"));
//        this.currentDirectory = new String(defaultDirectory);
        this.defaultDirectory = System.getProperty("user.dir" + "\\week3-study\\src");
        this.currentDirectory = defaultDirectory;
    }

    public void run() {
        while (status != ShellStatus.TERMINATE) {
            Scanner sc = new Scanner(System.in);
            char s = sc.nextLine().charAt(0);
            if (s == '\n') {
                status = ShellStatus.TERMINATE;
            }
        }
    }
}
