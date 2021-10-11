package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_1922 {
    static class Edge implements Comparable<Edge> {
        int a,b,weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {  // 오름차순
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "a=" + a +
                    ", b=" + b +
                    ", weight=" + weight +
                    '}';
        }
    }

    static int N, M, total=0;
    static int[] parent;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }
        edges = new Edge[M];

        // 크루스칼 알고리즘 : MST를 찾는 알고리즘으로, 그리디한 선택을 바탕으로 알고리즘 진행
        // 1. 모든 간선에 대해서, 간선의 연결비용을 낮은 순으로 오름차순 정렬
        // 2. 정렬된 간선 순서대로 선택하면서, 간선의 양 끝 정점을 union 한다.
        int a,b,c;
        for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edges[m] = new Edge(a,b,c);
        }

        // 간선 비용 순으로 오름차순 정렬
        Arrays.sort(edges);

        // 낮은 비용부터 크루스칼 알고리즘 진행
        for(int i=0; i<M; i++) {
            // debug
            //System.out.println(edges[i].toString());

            if(find(edges[i].a) != find(edges[i].b)) {
                union(edges[i].a, edges[i].b);
                total += edges[i].weight;

                //printParent();
            }
        }

        System.out.println(total);
    }

    private static void printParent() {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++)
            sb.append(i + " | ");
        sb.append("\n");

        for(int i=1; i<=N; i++)
            sb.append(parent[i] + " | ");
        System.out.println(sb.toString());
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        parent[pa] = pb;
    }

    private static int find(int a) {
        if(parent[a] == a)
            return a;

        return parent[a] = find(parent[a]);
    }
}
