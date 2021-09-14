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

		
		// 1. 1Ä­ : ¹âÀº°Ô ÃÖ´ñ°ª, N==1ÀÌ¸é Á¾·á
		dp[1] = steps[1];
		if(N<=1) {
			System.out.println(dp[1]);
			return;
		}
		
		// 2. 2Ä­ : 1Ä­+2Ä­ ¿¬´Þ¾Æ ¹â±â°¡ ÃÖ´ë
		dp[2] = steps[1] + steps[2];
		
		// 3. 3Ä­ ~ NÄ­ : DP ÁøÇà
		for(int i=3; i<=N; i++) {
			// ÇöÀç °è´Ü Á¡¼ö + 2Ä­ Á¡ÇÁ vs 1Ä­ Á¡ÇÁ (¿¬´Þ¾Æ ¿Â °Å Á¦¿Ü)
			dp[i] = Math.max(steps[i]+dp[i-2], steps[i]+steps[i-1]+dp[i-3]);
			
			// debug
			System.out.print(i+"¹øÂ° ");
			for(int j=1; j<=N; j++)
				System.out.print(dp[j] + " ");
			System.out.println();
		}
		System.out.println(dp[N]);
	}
}
