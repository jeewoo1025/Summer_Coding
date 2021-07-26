package day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_2252 {
	static int N,M;
	static int A,B;

	static int[] indegree;
	static ArrayList[] edge;
	static Queue<Integer> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		// ArrayList 초기화
		edge = new ArrayList[N+1];
		for(int i=1; i<=N ; i++) {
			edge[i] = new ArrayList<Integer>();
		}

		// 초기화
		indegree = new int[N+1];

		M = Integer.parseInt(st.nextToken());
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());

			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			// a < b
			edge[A].add(B);
			indegree[B] += 1;
		}

		// 진입차수 0인 얘들 Queue에 넣기
		queue = new LinkedList<Integer>();
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0)
				queue.add(i);
		}

		// Queue에서 1개씩 뽑으면서 연결된 간선 끊기
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			int now = queue.poll();

			sb.append(now + " ");

			int size = edge[now].size();
			for(int i=0; i<size; i++) {
				// 연결된 간선 찾기
				int target = (int)edge[now].get(i);

				// 해당 간선 끊기
				indegree[target]-=1;
				if(indegree[target]==0)
					queue.add(target);
			}
		}

		System.out.println(sb.toString());
	}

}
