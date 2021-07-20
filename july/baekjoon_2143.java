import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 백준 2143번
public class ex5_0720 {
	static long[] A,B;
	static long T;
	static int N,M;

	// 슬라이딩 윈도우
	// 결과 Big O is N^2

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력
		T = Long.parseLong(st.nextToken());

		N = Integer.parseInt(br.readLine());
		A = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			A[i] = Long.parseLong(st.nextToken());

		M = Integer.parseInt(br.readLine());
		B = new long[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			B[i] = Long.parseLong(st.nextToken());

		// subA, subB 구하기 (N^2)
		List<Long> subA = new ArrayList<Long>();
		List<Long> subB = new ArrayList<Long>();

		for(int i=0; i<N; i++) {
			long sum = A[i];
			subA.add(sum);
			for(int j=i+1; j<N; j++) {
				sum += A[j];
				subA.add(sum);
			}
		}

		for(int i=0; i<M; i++) {
			long sum = B[i];
			subB.add(sum);
			for(int j=i+1; j<M; j++) {
				sum += B[j];
				subB.add(sum);
			}
		}

		// subA는 오름차순, subB는 내림차순 정렬 (nlogn)
		Collections.sort(subA);
		Collections.sort(subB, Collections.reverseOrder());

		long result = 0;

		int ptA=0, ptB=0;
		while(ptA < subA.size() && ptB < subB.size()) {
			long currentA = subA.get(ptA);
			long target = T-currentA;

			if(subB.get(ptB) > target) {
				ptB++;
			} else if(subB.get(ptB) == target) {
				long cntA=0, cntB=0;

				while(ptA<subA.size() && subA.get(ptA)==currentA) {
					ptA++; // 2
					cntA++; //2
				}

				while(ptB<subB.size() && subB.get(ptB)==target) {
					ptB++;
					cntB++; //1
				}

				result += cntA*cntB;
			} else {  // < 작은 경우
				ptA++;
			}
		}

		System.out.println(result);
	}
}
