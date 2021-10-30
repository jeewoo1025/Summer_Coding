package basic;

import java.io.*;
import java.util.*;

public class bj_11438 {
    static int N,M,K=0;

    // LCA 변수
    static int[] depth;
    static int[][] parent; // 희소테이블, parent[K][V] : 정점 V의 2^k번째 조상정점 번호

    static ArrayList[] tree; // 1~N번 노드마다 연결되있는 노드들

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 1. 입력 & 초기화
        N = Integer.parseInt(br.readLine());

        // 2^K > N인 K찾기
        for(int i=1; i<=N; i*=2)
            K++;

        // LCA 초기화
        depth = new int[N+1];
        parent = new int[K][N+1];

        // TREE
        tree = new ArrayList[N+1];
        for(int i=1; i<=N; i++)
            tree[i] = new ArrayList<Integer>();

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            // 양방향 간선
            tree[v1].add(v2);
            tree[v2].add(v1);
        }

        // 2. DETPH 확인
        dfs(1,1);

        // 3. 2^N까지 parent 채우기
        fillParent();

        // LCA 진행
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            // 공통 조상 출력
            sb.append(LCA(v1, v2)+"\n");
        }

        System.out.println(sb);
    }

    // 최소 공통 조상
    private static int LCA(int a, int b) {
        // 1. depth[a] >= depth[b]이도록 조정하기
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 2. 더 깊은 a를 2^K승 점프하여 depth 맞추기
        for(int i=K-1; i>=0 ; i--) {
            if(Math.pow(2, i) <= depth[a]-depth[b])
                a = parent[i][a];
        }

        // 3. depth를 맞췄는데 같다면 종료
        if(a==b)
            return a;

        // 4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
        for(int i=K-1; i>=0; i--) {
            if(parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    // 부모 채우기
    private static void fillParent() {
        for(int i=1; i<K; i++) {
            for(int j=1; j<=N; j++) {  // 1~N번까지 정점 번호
                // 정점 N의 2^K번째 조상 정점 번호
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
    }

    // DEPTH 확인 DFS
    static void dfs(int id, int cnt) {
        // 1. depth 기록
        depth[id] = cnt;

        // 2. 자식들의 depth 기록
        for(int i=0; i<tree[id].size(); i++) {
            int next = (Integer)tree[id].get(i);
            if(depth[next] == 0) {
                dfs(next, cnt+1);
                parent[0][next] = id;  // 바로 위(1번째 부모를 기록)
            }
        }
    }
}
