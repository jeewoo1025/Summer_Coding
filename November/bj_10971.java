package basic;

import java.io.*;
import java.util.*;

public class bj_10971 {
    static int N;
    static boolean[] visited;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 외판원 순회
        for(int i=0; i<N; i++) {
            visited[i] = true;
            dfs(i, i, 0, 0);
            visited[i] = false;
        }

        System.out.println(answer);
    }

    public static void dfs(int start, int now, int sum, int cnt) {
        if(cnt==N-1) {
            if(map[now][start] != 0)
                answer = Math.min(sum+map[now][start], answer);

            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i] && map[now][i]!=0) {
                visited[i] = true;
                dfs(start, i, sum+map[now][i], cnt+1);
                visited[i] = false;
            }
        }
    }
}
