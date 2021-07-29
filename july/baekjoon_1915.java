package day7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon_1915 {
	static int N,M;
	static int[][] map, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		String input;
		for(int i=1; i<=N; i++) {
			input = br.readLine();
			for(int j=1; j<=M; j++)
				dp[i][j] = map[i][j] = input.charAt(j-1)-'0';
		}
		printDp();

		int max_length = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == 1) {
					dp[i][j] = check(i,j);
					if(max_length <= dp[i][j])
						max_length = dp[i][j];
					printDp();
				}
			}
		}

		System.out.println(max_length*max_length);
	}

	static void printDp() {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++)
				sb.append(dp[i][j] + " ");
			sb.append("\n");
		}
		sb.append("\n\n");
		System.out.println(sb.toString());
	}

	static int check(int y, int x) {
		// 좌 위 대각선, 좌, 위
		int check = dp[y-1][x-1];
		int check2 = dp[y][x-1];
		int check3 = dp[y-1][x];

		// 하나라도 0이면 크기 1짜리 정사각형
		if(check==0 || check2==0 || check3 == 0)
			return 1;

		// 3개 중에 정사각형이 존재하면 그 최솟값보다 1 큰 정사각형
		return Math.min(Math.min(check, check2), check3) + 1;
	}
}
