import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2003번
// 슬라인딩 윈도우
public class ex3_0720 {
	static int N,M;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			nums[i] = Integer.parseInt(st.nextToken());

		int left=0, right=0, sum=0, count=0;
		sum = nums[0];
		while(true) {
			if(sum==M) {
				count += 1;
				sum -= nums[left++];
			} else if(sum>M) {
				sum -= nums[left++];
			} else {
				sum += nums[++right];  // 다음 꺼 right를 넣어줌
			}

			if(right==N) {
				break;
			}
		}

		System.out.println(count);
	}
}
