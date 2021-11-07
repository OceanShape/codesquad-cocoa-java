import java.util.Scanner;

import static java.lang.Math.max;

public class Main {

    public static int[][] board1 = new int[21][21];
    public static int[][] board2 = new int[21][21];
    public static int n;

    public static void rotate(){
        int[][] tmp = new int[21][21];
        for(int i = 0; i < n; i++)
            System.arraycopy(board2[i], 0, tmp[i], 0, n);
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board2[i][j] = tmp[n-1-j][i];
    }

    public static void push(int dir){
        while(dir-- > 0) rotate();
        for(int i = 0; i < n; i++){
            int[] tilted = new int[21];
            int idx = 0;
            for(int j = 0; j < n; j++){
                if(board2[i][j] == 0) continue;
                if (tilted[idx] == 0) {
                    tilted[idx] = board2[i][j];
                } else if (tilted[idx] == board2[i][j]) {
                    tilted[idx++] *= 2;

                } else{
                    tilted[++idx] = board2[i][j];
                }
            }
            for(int j = 0; j < n; j++) board2[i][j] = tilted[j];
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board1[i][j] = sc.nextInt();

        int mx = 0;
        for(int tmp = 0; tmp < 1024; tmp++){
            for(int i = 0; i < n; i++)
                System.arraycopy(board1[i], 0, board2[i], 0, n);
            int brute = tmp;
            for(int i = 0; i < 5; i++){
                int dir = brute % 4;
                brute /= 4;
                push(dir);
            }
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    mx = max(mx, board2[i][j]);
        }
        System.out.println(mx);
    }
}
