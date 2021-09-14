import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bj_2579 {
	static int N;
	static int[] steps, dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		steps = new int[N+1];
		dp = new int[N+1];
		
		for(int i=1; i<=N; i++)
			steps[i] = Integer.parseInt(br.readLine());

		
		// 1. 1ĭ : ������ �ִ�, N==1�̸� ����
		dp[1] = steps[1];
		if(N<=1) {
			System.out.println(dp[1]);
			return;
		}
		
		// 2. 2ĭ : 1ĭ+2ĭ ���޾� ��Ⱑ �ִ�
		dp[2] = steps[1] + steps[2];
		
		// 3. 3ĭ ~ Nĭ : DP ����
		for(int i=3; i<=N; i++) {
			// ���� ��� ���� + 2ĭ ���� vs 1ĭ ���� (���޾� �� �� ����)
			dp[i] = Math.max(steps[i]+dp[i-2], steps[i]+steps[i-1]+dp[i-3]);
			
			// debug
			System.out.print(i+"��° ");
			for(int j=1; j<=N; j++)
				System.out.print(dp[j] + " ");
			System.out.println();
		}
		System.out.println(dp[N]);
	}
}
