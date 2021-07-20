// baekjoon 1806번 부분합
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ex4_0720 {
	static int N,S;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		nums = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			nums[i] = Integer.parseInt(st.nextToken());

		int result = N+1;
		int left=0, right=0, sum=nums[0];
		while(true) {
			if(sum >= S) {
				if(result > (right-left+1))
					result = (right-left+1);
				//System.out.println("result : "+result+"  right: "+right+"  left:"+ left);
				sum -= nums[left++];
			} else {
				sum += nums[++right];
			}

			if(right==N)
				break;
		}

		if(result == (N+1))
			System.out.println(0);
		else
			System.out.println(result);
	}

}
