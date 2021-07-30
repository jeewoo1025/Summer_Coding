package day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon_2342 {
	static ArrayList<Integer> input;
	// dp[i][j][k] : i번째 스텝일 때 왼발, 오른발
	static int[][][] dp;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		input = new ArrayList<Integer>();
		while(true) {
			int dist = Integer.parseInt(st.nextToken());
			if(dist == 0)
				break;
			input.add(dist);
		}

		N = input.size();
		dp = new int[N+1][5][5];

		int total = DDR(0,0,0);
		System.out.println(total);

		// debug
//
//		int i,j,k;
//		while(true) {
//			st = new StringTokenizer(br.readLine());
//
//			i = Integer.parseInt(st.nextToken());
//			if(i < 0)
//				break;
//
//			j = Integer.parseInt(st.nextToken());
//			k = Integer.parseInt(st.nextToken());
//
//			System.out.println(dp[i][j][j]);
//		}
	}

	public static int DDR(int step, int left, int right) {
		//System.out.println("step : "+step+", left : "+left+"  right : "+right);

		// 1. 마지막 스템 도달하면 끝
		if(step == N)
			return 0;

		// 2. dp에 값이 차 있으면 return
		if(dp[step][left][right] != 0)
			return dp[step][left][right];

		// 3. 가능한 경우의 수 중 가장 적은 경우로 갱신하고 return
		// 왼발 vs 오른발
		int leftScore = score(left, input.get(step)) + DDR(step+1, input.get(step), right);
		int rightScore = score(right, input.get(step)) + DDR(step+1, left, input.get(step));

		return dp[step][left][right] = Math.min(leftScore, rightScore);
	}

	public static int score(int point, int dist) {
		int power = 0;

		if(point == dist) 	// 힘 1
			power = 1;
		else if(point == 0) // 힘 2
			power = 2;
		else if(Math.abs(point-dist)==2) {
			power = 4; 	// 힘 4
		} else {
			power = 3;
		}

		return power;
	}
}
