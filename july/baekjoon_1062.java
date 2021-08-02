package day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문제 의도 : K개의 알파벳을 배웠을 때 동시에 배울 수 있는 단어들의 수의 최댓값
public class baekjoon_1062 {
	static int N,K,result=0;
	static String[] words;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		// 1. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		words = new String[N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();

			// 접두사 anta, 접미사 tica는 제거
			str = str.substring(4, str.length()-4);
			words[i] = str;
		}

		// 2. anta, tica --> 'a', 'n', 't', 'i', 'c' (K는 5 이상)
		if(K<5) {
			System.out.println(0);
			return;
		} else if(K==26) {
			// 전부 익힐 수 있다.
			System.out.println(N);
			return;
		}

		// 재귀
		// 'a','c','i','t','n'는 포함되어 있다고 visited 배열에 표시
		visited = new boolean[26];
		visited['a'-'a'] = true;
		visited['c'-'a'] = true;
		visited['i'-'a'] = true;
		visited['t'-'a'] = true;
		visited['n'-'a'] = true;

		// 단어 학습
		learn(0, 0);

		// N개 중 읽을 수 있는 단어의 갯수
		System.out.println(result);
	}


	// 매개변수 - 배웠다고 표시해줄 알파벳, 익힌 알파벳의 수
	public static void learn(int alpha, int k) {
		if(k == (K-5)) { // 더 이상 익힐 수 없다
			int temp = 0;

			// debug
			StringBuilder sb = new StringBuilder();

			// 익힌 알파벳들로 배울 수 있는 단어들의 수를 셈
			for(String w:words) {
				boolean flag = true;

				for(int i=0; i<w.length(); i++) {
					if(!visited[w.charAt(i)-'a']) {
						flag = false;
						break;
					} else
						sb.append(w.charAt(i) + " ");
				}

				if(flag)
					temp += 1;
			}
			// debug
			// 최댓값 나오는경우
			if(temp > result) {
				sb.append("  갯수 : "+temp);
				//System.out.println(sb.toString());
			}

			result = Math.max(result, temp);
			return;
		}

		// 브루트포스를 통해 알파벳 하나하나를 배움 (actin을 제외한 모두(b,d,e,f,g,h...,x,y,z))
		// 재귀를 호출한 뒤 다시 배우지 않았다고 표시
		for(int c=alpha; c<26; c++) {
			if(!visited[c]) {
				visited[c] = true;
				learn(c, k+1);
				visited[c] = false;
			}
		}
	}
}
