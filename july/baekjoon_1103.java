package day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class answer_1103 {
	static int N,M;
	static int[][] map, dp;
	static int answer = 0;
	static boolean isINF = false;
	static boolean[][] visited;

	// 위 아래 좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		// 1. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];   // 지도
		visited = new boolean[N][M];  // 방문확인
		dp = new int[N][M]; // dp 저장

		// 초기화
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = (int)(str.charAt(j)-'0');
				dp[i][j] = 0;
			}
		}

		// DFS로 깊이우선탐색 + DP로 가지치ㅣㄱ
		visited[0][0] = true; // (0,0) 무한 loop 확인 용 true 마킹
		dfs(0,0,1); // (0,0) 출발

		if(isINF)  // 무한루프
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	static void dfs(int y, int x, int cnt) {
		if(cnt > answer)
			answer = cnt;

		// 가지치기 - dp 배열에 현재 cnt 마킹
		dp[y][x] = cnt;

		// 사방 확인
		for(int i=0; i<4; i++) {
			int num = map[y][x];
			int ny = dy[i]*num + y;
			int nx = dx[i]*num + x;

			// 지도 밖에 나감
			if(ny<0 || ny>=N || nx<0 || nx>=M)
				continue;

			// 구멍
			if(map[ny][nx]==(int)('H'-'0'))
				continue;

			// 이미 방문함
			if(visited[ny][nx]) {
				isINF = true;
				return;
			}

			// 최대한 많이 찾는 거기 때문에 PASS
			if(dp[ny][nx] > cnt)
				continue;

			visited[ny][nx] = true;
			dfs(ny, nx, cnt+1);
			visited[ny][nx] = false;
		}
	}
}
