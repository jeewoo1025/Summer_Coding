package day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1039번 교환

public class baekjoon_1039 {
	static int K,len,sol;
	static String N;
	static int[][] visit;  // [변경횟수][최대숫자]

	public static void main(String[] args) throws Exception {
		// 1. 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = st.nextToken();
		K = Integer.parseInt(st.nextToken());

		len = N.length();
		visit = new int[K+1][1000001]; // [변경횟수][최대숫자]

		sol = -1; // 연산 불가능
		sol = dfs(N,0);

		System.out.println(sol);
	}

	// i,j 위치를 바꿔줌
	static String swapLoc(String str, int i, int j) {
		char[] chars = str.toCharArray();
		char tmp = chars[i];
		chars[i] = chars[j];
		chars[j] = tmp;
		return String.valueOf(chars);
	}

	static int dfs(String strN, int depth) {
		int num = Integer.parseInt(strN);
		if(depth == K) {
			//System.out.println(num + " - depth : "+depth);
			return num;
		}

		int ret = visit[depth][num];

		// 같은 depth에서 방문한 이력이 있으면 메모지에이션 된 값 리턴
		if(ret != 0) {
			//System.out.println(num + " - depth : "+depth+ " ret : "+ret);
			return ret;
		}

		// 처음 방문한 경우, 안된다고 가정하고(-1) 시작
		ret = -1;
		for(int i=0; i<len-1; i++) {
			for(int j=i+1; j<len; j++) {
				// 0을 맨 앞으로 가져올 수 없음
				if(i==0 && strN.charAt(j)=='0')
					continue;

				String tmpStr = swapLoc(strN, i, j);

				int tmpRet = dfs(tmpStr, depth+1);
				if(tmpRet > ret) {
					ret = tmpRet;
				}
			}
		}
		visit[depth][num] = ret;
		return ret;
	}
}
