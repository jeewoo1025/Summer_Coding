package basic;

import java.io.*;
import java.util.*;

// 플로이드 - 와샬 알고리즘 : 모든 정점에서 모든 정점으로의 최단경로
// 시간복잡도 O(n^3)
public class bj_11404 {
    static int INF = Integer.MAX_VALUE;
    static int N,M;
    static long[][] d;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 초기화
        d = new long[N+1][N+1];
        init();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 정점
            int b = Integer.parseInt(st.nextToken()); // 도착 정점
            int c = Integer.parseInt(st.nextToken()); // 비용

            // 만약 값이 이미 입력됬다면 비교
            if(d[a][b] > c) {
                d[a][b] = c;
            }
        }

        floydWarshall();

        // 배열 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i==j || d[i][j] == INF)
                    sb.append(0 + " ");
                else
                    sb.append(d[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void init() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                d[i][j] = INF;
            }
        }
    }

    private static void floydWarshall() {
        for(int k=1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(d[i][k] + d[k][j] < d[i][j])
                        d[i][j] = d[i][k] + d[k][j];
                }
            }
        }
    }
}
