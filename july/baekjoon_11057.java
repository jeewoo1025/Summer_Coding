import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static long[][] dp;
	static long answer=0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 입력
		N = Integer.parseInt(br.readLine());

		// 2. dp[i][j]- i:N(자릿수), j:현재숫자
		dp = new long[N+1][10];

		// 초기화
		for(int i=0; i<10; i++)
			dp[1][i] = 1;

		if(N<=1) {
			answer = getSum();
			System.out.println(answer);
			return;
		}

		// 3. dp[i][j] : i = step, j = 0~9 현재 step이 가리키는 숫자
		for(int i=2; i<=N; i++) {
			for(int j=0; j<=9; j++) {
				if(j==0) {
					// 3-1. 경우의 수 1개. 무조건 0, 00, 000, 0000 밖에 안되기 때문에,,,
					dp[i][j] = 1;
				}
				else {
					// 3-2. 점화식 dp[i][j] = dp[i-1][j] + dp[i][j-1]
					dp[i][j] = (dp[i-1][j]+dp[i][j-1])%10007;
				}
			}
		}

		// 경우의 수 모두 합하기
		answer = getSum();
		System.out.println(answer);
	}

	// 모든 경우의 수 합 구하기
	static long getSum() {
		long answer = 0;
		for(int j=0; j<=9; j++) {
			answer += dp[N][j];
		}
		answer %= 10007;
		return answer;
	}
}