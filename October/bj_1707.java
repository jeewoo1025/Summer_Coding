package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1707 {
    static final int RED=1, BLUE=-1;

    static int V,E;
    static int[] colors;    // 색 : RED (1), BLUE(-1)
    static ArrayList<Integer>[] edges;  // i번째 노드와 연결되어 있는 노드들

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int testcase=0; testcase<T; testcase++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 정점 1~V번
            E = Integer.parseInt(st.nextToken());

            // 초기화
            colors = new int[V+1];
            edges = new ArrayList[V+1];
            for(int i=1; i<=V; i++)
                edges[i] = new ArrayList<>();

            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                edges[u].add(v);
                edges[v].add(u);
            }

            // 1이분 그래프 판별
            boolean flag = true;
            for(int i=1; i<=V; i++) {
                // 방문하지 않은 정점에 대해서 탐색 수행
                if(colors[i] == 0)
                    flag = bfs(i, RED);

                if(!flag)  // 이분그래프 아니면 탈출
                    break;
            }

            // 답변 출력
            String answer = flag ? "YES":"NO";
            sb.append(answer+"\n");
        }
        System.out.println(sb);
    }

    private static boolean bfs(int node, int color) {
        Queue<Integer> queue = new LinkedList<>();

        // Root 정점 넣기
        queue.offer(node);
        colors[node] = color;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            // 인접한 노드들 큐에 넣기
            for(int adj:edges[now]) {
                if(colors[adj]==0) { // 방문하지 않은 정점이면
                    queue.offer(adj);
                    colors[adj] = colors[now]*-1;  // 다른 색 지정
                } else if(colors[adj]+colors[now] != 0) {
                    // 서로 인접한 색이 같은 색이면 이분 그래프가 아님
                    return false;
                }
            }
        }

        return true; // 이분 그래프임
    }
}
