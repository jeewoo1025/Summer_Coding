import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_11049 {
	static class info {
		int row, col;

		public info(int r, int c) {
			this.row = r;
			this.col = c;
		}
	}

	static int N;
	static int[][] dp;
	static info[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 1. 입력 받기
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][N+1];
		input = new info[N+1];

		int r,c;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			input[i] = new info(r,c);

			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		divide_and_conquer(1, N);
		System.out.println(dp[1][N]);
	}

	static int divide_and_conquer(int start, int end) {
		// 자기 자신일 때는 0 리턴
		if(start==end)
			return 0;

		// 갱신된적이 있으면 다시 하지 않음
		if(dp[start][end] != Integer.MAX_VALUE) {
			return dp[start][end];
		}

		// dp[i][j] : i~j까지 행렬곱의 최솟값
		int left, right;
		for(int i=start; i<end; i++) {
			left = divide_and_conquer(start, i); // start~i까지 행렬 곱
			right = divide_and_conquer(i+1, end); // i+1~end까지 행렬곱

			dp[start][end] = Math.min(dp[start][end],
					left+right+(input[start].row*input[i].col*input[end].col));
		}

		return dp[start][end];
	}
}
