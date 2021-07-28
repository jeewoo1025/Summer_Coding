package day7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 거의 최단경로

public class baekjoon_5719 {
	static class Edge implements Comparable<Edge> {
		int id, cost;

		public Edge(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) { // 내림 차순
			return this.cost - o.cost;
		}
	}

	static int N,M,S,D; // N - 정점수, M - 간선 수, S - 출발점, D - 도착점
	static int[] dist;
	static ArrayList[] adjList;
	static int ans;

	// 거의 최단경로 전용변수
	static boolean[][] isShortest;
	static ArrayList[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		while(N!=0 && M != 0) {
			// 정점은 0~N-1로 구성
			// 1. 각종 배열 초기화
			isShortest = new boolean[N][N];
			parent = new ArrayList[N];
			adjList = new ArrayList[N];
			for(int i=0; i<N; i++) {
				parent[i] = new ArrayList<Integer>();
				adjList[i] = new ArrayList<Edge>();
			}

			// 2. 입력
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			// 3. 방향 간선 인접리스트 입력
			int u,v,p;
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());

				// 단방향 간선
				adjList[u].add(new Edge(v,p));
			}

			// 4. 출발지부터 다익스트라 진행
			dijkstra(S,D);
			backTracking(S,D);
			dijkstra(S,D);

			// 5. 정답 출력
			if(dist[D] == Integer.MAX_VALUE)
				sb.append("-1\n");
			else
				sb.append(dist[D] + "\n");

			// 다음 N,M 입력 - 0,0 입력시 종료
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		}

		System.out.println(sb.toString());
	}

	// dest에서 start 방향으로 tracking
	static void backTracking(int start, int now) {
		if(start == now)  // 도착
			return;

		int len = parent[now].size();
		for(int i=0; i<len; i++) {
			int next = (int)parent[now].get(i);

			if(!isShortest[next][now]) {
				isShortest[next][now] = true; // 최단경로라고 표시해줌
				backTracking(start, next);	  // dfs 추가 진행
			}
		}
	}

	static void dijkstra(int start, int dest) {
		// 다익스트라 2번 들리므로 함수 내에서 int로 초기화
		dist = new int[N];
		for(int i=0; i<N; i++)
			dist[i] = Integer.MAX_VALUE;

		// 1. 출발지 비용은 0으로 하고 출발지를 PQ에 넣고 시작
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		dist[start] = 0;
		pq.add(new Edge(start, 0));

		while(!pq.isEmpty()) {
			Edge now = pq.poll();

			// 특정 목적지에 도착하는 문제였다면, 도착지 도착후 break
			// 2. 더 큰 가중치로 도착한 경우 pass
			if(now.cost > dist[now.id])
				continue;

			// 3. 현재 위치에 연결된 간선 탐색
			int len = adjList[now.id].size();
			for(int i=0; i<len; i++) {
				Edge next = (Edge)adjList[now.id].get(i);

				// 최단경로 간선이면 continue - 2번째 다익스트라를 위한 로직
				if(isShortest[now.id][next.id])
					continue;

				// 최단경로를 모두를 넣어줘야 하므로, 다른 최단경로일 경우 백트래킹 대상(parent)에 추가
				// 최단경로 동일한 값들을 기록한다.
				if(dist[next.id] == now.cost + next.cost)
					parent[next.id].add(now.id);

				// 4. cost가 더 작을 때만 갱신하고 PQ에 넣기
				else if(dist[next.id] > now.cost + next.cost) {
					dist[next.id] = now.cost + next.cost;

					// 새로운 parent가 생겼으므로 비어주고 넣기
					parent[next.id].clear();
					parent[next.id].add(now.id);
					pq.add(new Edge(next.id, dist[next.id]));
				}
			}
		}
	}
}

