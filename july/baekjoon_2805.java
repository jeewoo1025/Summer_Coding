import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ex6_0720 {
	static int N;
	static long M;
	static long trees[];

	// 백준 나무자르기 2805번
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());

		long max=0;
		trees = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trees[i] = Long.parseLong(st.nextToken());
			max = Math.max(trees[i], max);
		}

		long result = 0;
		long left=0, right=max;
		while(left<=right) {
			long total = 0;
			long mid = (left+right)/2;

			for(long t:trees) {
				if(t>mid)
					total += (t-mid);
			}

			if(total<M)  // 나무를 낮춰야 함.
				right = mid-1;
			else {
				result = mid;
				left = mid+1;
			}
		}

		System.out.println(result);
	}

}
