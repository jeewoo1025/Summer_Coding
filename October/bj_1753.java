import java.io.*;
import java.util.*;

public class bj_1753 {
    static class Edge implements Comparable<Edge> {
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {  // 오름차순
            return this.weight - o.weight;
        }
    }

    static int V, E;
    static int INF = Integer.MAX_VALUE;
    static int[] dist;
    static boolean[] isVisited;
    static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 초기화
        isVisited = new boolean[V+1];

        dist = new int[V+1];
        edges = new ArrayList[V+1];
        for(int i=1; i<=V; i++) {
            dist[i] = INF;
            edges[i] = new ArrayList<>();
        }

        // 시작 노드, 간선들 입력
        int start = Integer.parseInt(br.readLine());
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // u-->v (가중치 w)
            edges[u].add(new Edge(v, w));
        }

        dijkstra(start);

        // 1 ~ V개의 노드에서 시작점 ~ i번째 노드에서의 최단경로 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=V; i++) {
            if(dist[i] == INF)
                sb.append("INF" + "\n");
            else
                sb.append(dist[i] + "\n");
        }
        System.out.println(sb);
    }

    public static void dijkstra(int start) {
        Queue<Edge> pq = new PriorityQueue<>();

        // 시작 노드를 큐에 삽입
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Edge now = pq.poll();

            if(isVisited[now.dest])  // 방문한 곳이라면
                continue;
            isVisited[now.dest] = true;

            for(Edge edge:edges[now.dest]) {
                // 선택된 노드의 인접노드
                int next = edge.dest;

                // 선택된 노드를 인접 노드로 거쳐서 가는 비용
                int nextWeight = now.weight + edge.weight;

                // 기존의 최소 비용보다 더 저렴하다면 교체
                if(nextWeight < dist[next]) {
                    dist[next] = nextWeight;
                    pq.add(new Edge(next, nextWeight));
                }
            }
        }
    }
}
