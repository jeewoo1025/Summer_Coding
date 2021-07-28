package day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 벨만 포드 알고리즘
// 백준 11657번 타임 머신
public class baekjoon_11657 {
	static int N, M;

	static final int INF=Integer.MAX_VALUE;
	static long[] dist;
	static Edge[] egList;
	static int ans; // 출력할 답
	static boolean infFlag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		egList = new Edge[M+1];

		int A,B,C;
		for(int m=1; m<=M; m++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			egList[m] = (new Edge(A,B,C));
		}

		// 2. 벨만 포드
		dist = new long[N+1];
		dist[1] = 0;
		for(int i=2; i<=N; i++) {
			dist[i] = INF;
		}

		BellmanFord();

		// 3. 출력
		StringBuilder sb = new StringBuilder();

		// 3-1. 무한루프가 가능하면 -1 출력
		if(infFlag)
			sb.append("-1");

		// 3-2. 무한루프가 없으면 모든 도시의 최솟값 출력
		else {
			for(int i=2; i<=N; i++) {
				if(dist[i] == INF)
					sb.append("-1\n");
				else
					sb.append(dist[i]+"\n");
			}
		}

		System.out.println(sb.toString());
	}

	static void BellmanFord() {
		// 1. N = 1번 동안 간선 M을 모두 확인하기
		for(int i=1; i<N; i++) {
			for(int j=1; j<=M; j++) {
				Edge now = egList[j];

				// 1-1. 출발지가 현재 무한대이면 continue
				if(dist[now.start] == INF)
					continue;

				// 1-2. 최솟값으로 값 갱신 가능하면 갱신
				dist[now.target] = Math.min(dist[now.target], dist[now.start]+ now.cost);
			}
		}

		// 2. 마지막으로 간선 M을 모두 확인해서 갱신이 발생하면 무한루프
		for(int j=1; j<=M; j++) {
			Edge now = egList[j];

			if(dist[now.start] == INF)
				continue;

			// 갱신이 발생한다면 무한루프에 빠질 수 있음
			if(dist[now.start] + now.cost < dist[now.target]) {
				infFlag = true;
				return;
			}
		}
	}
}

class Edge {
	int start;
	int target;
	int cost;

	public Edge(int start, int target, int cost) {
		this.start = start;
		this.target = target;
		this.cost = cost;
	}
}
