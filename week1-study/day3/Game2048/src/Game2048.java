import java.util.Scanner;

import static java.lang.Math.max;

public class Game2048 {

    public static int[][] origin = new int[21][21];
    public static int[][] testCase = new int[21][21];
    public static int n;

    public static void rotate(){
        int[][] tmp = new int[21][21];
        for(int i = 0; i < n; ++i)
            System.arraycopy(testCase[i], 0, tmp[i], 0, n);
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < n; ++j)
                testCase[i][j] = tmp[n-j-1][i];
    }

    public static void push(int dir){
        while(dir-- > 0) rotate();
        for(int i = 0; i < n; ++i){
            int[] res = new int[21];
            int idx = 0;
            for(int j = 0; j < n; ++j){
                if(testCase[i][j] == 0) continue;
                if (res[idx] == 0) {
                    res[idx] = testCase[i][j];
                } else if (res[idx] == testCase[i][j]) {
                    res[idx++] *= 2;
                } else{
                    res[++idx] = testCase[i][j];
                }
            }
            for(int j = 0; j < n; ++j) testCase[i][j] = res[j];
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for(int i = 0; i < n; ++i)
            for(int j = 0; j < n; ++j)
                origin[i][j] = sc.nextInt();

        int mx = 0;
        for(int com = 0; com < 1024; com++){
            for(int i = 0; i < n; ++i)
                System.arraycopy(origin[i], 0, testCase[i], 0, n);
            int tmp = com;
            for(int i = 0; i < 5; ++i){
                int dir = tmp % 4;
                tmp /= 4;
                push(dir);
            }
            for(int i = 0; i < n; ++i)
                for(int j = 0; j < n; ++j)
                    mx = max(mx, testCase[i][j]);
        }
        System.out.println(mx);
    }
}
