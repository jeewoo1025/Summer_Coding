import java.io.*;
import java.util.*;

public class bj_2096 {
    static int N;
    static long[][] dpMax, dpMin;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dpMax = new long[N][3];
        dpMin = new long[N][3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                long temp = Long.parseLong(st.nextToken());
                dpMax[i][j] = temp;
                dpMin[i][j] = temp;
            }
        }

        // 1줄 밖에 없을 때
        if(N<=1) {
            printAnswer(0);
            return;
        }

        // 1 ~ N-1줄
        for(int i=1; i<N; i++) {
            // 최대 왼, 중, 오
            dpMax[i][0] += Math.max(dpMax[i-1][0], dpMax[i-1][1]);
            dpMax[i][1] += Math.max(dpMax[i-1][1], Math.max(dpMax[i-1][0], dpMax[i-1][2]));
            dpMax[i][2] += Math.max(dpMax[i-1][2], dpMax[i-1][1]);

            // 최소 왼, 중, 오
            dpMin[i][0] += Math.min(dpMin[i-1][0], dpMin[i-1][1]);
            dpMin[i][1] += Math.min(dpMin[i-1][1], Math.min(dpMin[i-1][0], dpMin[i-1][2]));
            dpMin[i][2] += Math.min(dpMin[i-1][2], dpMin[i-1][1]);
        }

        printAnswer(N-1);
    }

    public static void printAnswer(int line) {
        // 최대값 출력
        long max = Math.max(dpMax[line][0], Math.max(dpMax[line][1], dpMax[line][2]));

        // 최소값 출력
        long min = Math.min(dpMin[line][0], Math.min(dpMin[line][1], dpMin[line][2]));

        System.out.println(max + " " + min);
    }
}
