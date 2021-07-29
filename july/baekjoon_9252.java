package day9;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class baekjoon_9252 {
	static int N,M;
	static int[][] dp;
	static String inputA, inputB;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 입력
		inputA = br.readLine();
		inputB = br.readLine();
		N = inputA.length();
		M = inputB.length();

		// 2. 길이 구하기
		int len = getLCSLength();

		// 3. 문자열 구하기
		StringBuilder sb = new StringBuilder();
		while(N!=0 && M!=0) {
			if(inputA.charAt(N-1) == inputB.charAt(M-1)) {
				sb.insert(0,  inputA.charAt(N-1)); // 첫번째 인자로 삽입

				// 대각선으로 이동 ↖
				N -= 1;
				M -= 1;
			} else if(dp[N][M] == dp[N-1][M]) { // ←
				N -= 1;
			} else if(dp[N][M] == dp[N][M-1]) { // ↑
				M -= 1;
			}
		}
		System.out.println(len + "\n" + sb.toString());
	}

	static int getLCSLength() {
		dp = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				// 2-1. 같으면 추가
				if(inputA.charAt(i-1) == inputB.charAt(j-1))
					dp[i][j] = dp[i-1][j-1]+1;

				// 2-2. 다르면
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		return dp[N][M];
	}
}
