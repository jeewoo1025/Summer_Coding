package day7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1932 {
	static int N, ans;
	static int[][] map, dp;

	public static void main(String[] args) throws Exception{
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		dp = new int[N+1][N+1];

		// 1. 입력
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=i; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		// 2. 1~(N-1) Row에서 뿌려주는 DP 실행
		dp[1][1] = map[1][1];
		for(int i=1; i<=N-1; i++) {
			for(int j=1; j<=i; j++) {
				// 가능한 최댓값으로 갱신
				// 바로 밑(↓)에 칸과 비교
				dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);

				// 우측 대각선 아래(↘) 칸과 비교
				dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]);
			}

			// 해당 칸 점수(map)를 dp에 더해줌
			for(int j=1; j<=i+1; j++) {
				dp[i+1][j] += map[i+1][j];
			}
		}

		// 3. 마지막 줄에서 가장 큰 값을 찾아서 출력
		for(int j=1; j<=N; j++)
			ans = Math.max(ans,  dp[N][j]);

		System.out.println(ans);
	}
}
