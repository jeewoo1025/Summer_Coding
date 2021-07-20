import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ex7_0720 {
	static long X,Y;

	// 백준 게임 1072번
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		X = Long.parseLong(st.nextToken());
		Y = Long.parseLong(st.nextToken());

		// 형택이가 게임을 최소 몇 번 더 해야 승률 Z가 변하는가?
		long Z = ((Y*100)/X);
		if(Z>=99) {
			System.out.println(-1);
			return;
		}

		// 1~최대 X번까지 수행하기
		// 100%, 99%는 절대로 안 변함

		// 승률이 그대로면 오른쪽
		// 승률이 높거나 같으면 왼쪽
		long start=1, end=X;
		while(start<end) {
			long mid = (start+end)/2;

			long val = ((Y+mid)*100)/(X+mid);
			if(val == Z) {
				//System.out.println("승률이 그대로 : "+val);
				start = mid+1;
			} else {
				//System.out.println("승률이 높거나 같아짐 : "+val+" result : "+mid);
				end = mid-1;
			}
		}

		System.out.println(end);
	}

}
