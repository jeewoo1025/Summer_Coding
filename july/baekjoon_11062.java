import java.io.*;
import java.util.*;

// 11062 카드 게임
public class Main {

	static int T, N; // T - 테스트케이스 개수, N 카드 개수
	static int[] input;
	static int[][][] dp; // dp [ 0 근우, 1 명우 ] [ 출발범위 ] [ 도착범위 ]

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 1. 입력받기
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			input = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}

			dp = new int[2][N + 1][N + 1];
			int ans = cardGame(1, N, 0);

			bw.write(ans+"\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	// 출발, 도착, flag 0 근우, 1 명우
	static int cardGame(int start, int end, int flag) {

		// 1. 마지막 카드라면
		if (start == end) {
			// 1-1. 근우 차례면
			if (flag == 0) {
				return dp[flag][start][end] = input[start];
			}
			// 1-2. 명우 차례면
			else {
				return dp[flag][start][end] = 0;
			}
		}

		// 2. 이미 알고 있으면 가지치기
		if (dp[flag][start][end] != 0)
			return dp[flag][start][end];

		// 3. 게임 진행을 통해 값을 알아야하면 분할정복 진행
		// 3-1. 근우 차례면
		if (flag == 0) {
			dp[flag][start][end] = Math.max(cardGame(start + 1, end, 1) + input[start], cardGame(start, end - 1, 1) + input[end]);
		}
		// 3-2. 명우 차례면
		else {
			dp[flag][start][end] = Math.min(cardGame(start + 1, end, 0), cardGame(start, end - 1, 0));
		}
		return dp[flag][start][end];
	}
}