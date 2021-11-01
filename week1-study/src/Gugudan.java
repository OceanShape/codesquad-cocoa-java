import java.util.Scanner;

public class Gugudan {

    private int start;
    private int end;

    public boolean getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("시작 단과 끝 단 입력(1-9): ");

        int start = sc.nextInt();
        int end = sc.nextInt();
        if (start < 1 || end > 9 || start > end) {
            System.out.println("유효하지 않은 입력입니다.");
            return false;
        }
        this.start = start;
        this.end = end;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = start; i <= end; ++i){
            for(int j = 1; j <= 9; ++j){
                sb.append(String.format("\t%dx%d=%d", i, j, i*j));
            }
            sb.append(String.format("\n"));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Gugudan g = new Gugudan();

        do {
        } while (!g.getInput());

        System.out.println(g.toString());
    }
}
