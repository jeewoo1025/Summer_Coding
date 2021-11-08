package basic;

import java.io.*;
import java.util.*;

public class bj_1405 {
    static int MAX_SIZE=30;
    static int N;
    static double[] prob;
    static double total=0;
    static boolean[][] visited = new boolean[MAX_SIZE][MAX_SIZE];

    // 동 서 남 북
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        prob = new double[4];

        // 동 서 남 북
        for(int i=0; i<4; i++) {
            prob[i] = Double.parseDouble(st.nextToken()) / 100;
        }

        // 시작점 = (15,15)
        visited[15][15] = true;
        dfs(15, 15, 0, 1);

        System.out.println(total);
    }

    public static void dfs(int x, int y, int level, double curProb) {
        if(level == N) {
            total += curProb;
            return;
        }

        for(int i=0; i<4; i++) {
            int nx=x+dx[i];
            int ny=y+dy[i];

            // 범위 밖
            if(nx<0 || nx>=MAX_SIZE || ny<0 || ny>=MAX_SIZE)
                continue;

            // 특정 지점을 방문 X
            if(!visited[nx][ny] && prob[i]>0) {
                visited[nx][ny] = true;
                dfs(nx, ny, level+1, curProb*prob[i]);
                visited[nx][ny] = false;
            }
        }
    }
}
