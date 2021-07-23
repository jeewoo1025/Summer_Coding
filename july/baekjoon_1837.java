package day4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 1837번 암호제작

public class baekjoon_1837 {
	static int K;
	static int MAX = 1000000;
	static char[] P;
	static boolean[] checked;
	static List<Integer> primes = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		P = st.nextToken().toCharArray();
		K = Integer.parseInt(st.nextToken());

		checked = new boolean[MAX+1];

		// 에라토스테네스의 체
		for(int i=2; i < MAX+1; i++) {
			if(!checked[i]) {
				primes.add(i);  // 소수 추가

				// i의 모든 배수들을 제거
				for(int j=i*2; j<=MAX; j+=i) {
					checked[j] = true;  // 합성수다
				}
			}
		}

		for(int prime:primes) {
			if(prime >= K)
				break;

			if(checkIsBad(prime)) {
				System.out.println("BAD " + prime);
				return;
			}
		}

		System.out.println("GOOD");
	}

	static boolean checkIsBad(int x) {
		int result  = 0;

		for(int i=0; i<P.length; i++) {
			// 모듈러 연산
			result = (result*10+(P[i]-'0'))%x;
		}

		if(result == 0)  // 나눠지면 나쁜 것!!
			return true;
		return false;
	}
}
