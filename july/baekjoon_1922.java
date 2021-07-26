package day5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon_1922 {
	static int N,M;
	static int cnt=0, ans=0;
	static int parent[];
	static PriorityQueue<Edge> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		// 초기화
		parent = new int[N+1];
		for(int i=1; i<=N; i++)
			parent[i] = i;

		// M만큼 입력
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(a, b, c)); // cost 오름차순으로 자동 정렬
		}

		// 모든 간선을 cost 오름차순으로 정렬
		// 간선을 queue에서 뽑았을 때
		while(!pq.isEmpty()) {
			Edge now = pq.poll();

			// 가지치기
			if(cnt == N-1)
				break;

			// 두 정점을 이미 연결 O --> 생략
			if(now.start == now.end)
				continue;

			// else, 간선 추가후 두 정점을 union
			if(find(now.start) != find(now.end)) {
				cnt+=1;
				Union(now.start, now.end);
				ans += now.cost;
			}
		}

		System.out.println(ans);
	}

	static int find(int a) {
		if(parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	static void Union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		parent[pa] = pb;
	}
}

class Edge implements Comparable<Edge> {
	int start;
	int end;
	int cost;

	public Edge(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {  // 오름차순
		return this.cost - o.cost;
	}
}