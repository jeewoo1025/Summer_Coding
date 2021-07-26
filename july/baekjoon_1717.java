package day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 유니온 파인드, 서로소 집합
public class baekjoon_1717 {
	static int[] parent;
	static int N, M;
	static int val,a,b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		// 초기화
		parent = new int[N+1];
		for(int i=1; i<=N; i++)
			parent[i] = i;

		M = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());

			val = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if(val == 0) { // 합집합
				Union(a,b);
			} else if(val == 1) {
				// 같은 집합인지 확인하는 경우 - Find
				if(find(a) == find(b))
					sb.append("YES\n");
				else
					sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
	}

	static int find(int a) {
		if(parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	static void Union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		// a 그룹에 포함된 모든 정점을 b와 같은 그룹으로 만들기 위해서
		parent[pa] = pb;
	}
}
