// 백준 구간 합구하기 2042번
package day2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class num4 {
	static int S;
	static int N,M,K;
	static long[] nums, trees;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("C:\\Users\\jeewo\\eclipse-workspace\\Weclom\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new long[N];

		for(int i=0; i<N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}

		S = 1;
		while(S<N) {
			S*=2;
		}
		trees = new long[S*2];

		// Tree 초기화
		initBU();
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(a==1) { // update
				long c = Long.parseLong(st.nextToken());
				long diff = c - trees[S+b-1];
				update(1, S, 1, b, diff);
			} else { // query로 구간합
				int c = Integer.parseInt(st.nextToken());
				sb.append(Long.toString(query(1,S,1,b,c)) + "\n");
			}
		}

		System.out.println(sb.toString());
	}

	static void initBU() {  // Bottom - Up 방식
		// Leaf에 값 반영
		for(int i=0; i<N; i++)
			trees[S+i] = nums[i];

		// 내부 노드 채움
		for(int i=S-1; i>=1; i--)
			trees[i] = trees[i*2] + trees[i*2+1];
	}

	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		if(right < queryLeft || left > queryRight) {
			// 연관이 없음 -> 결과에 영향이 없는 값 return
			return 0;
		} else if(left >= queryLeft && right <= queryRight) {
			// case 실제로 의미 있는 값) 판단 가능 -> 현재 노드 값 return
			return trees[node];
		} else {
			// 판단불가, 자식에게 위임, 자식에게 올라온 힙을 return
			int mid = (left + right)/2;
			long leftResult = query(left, mid, node*2, queryLeft, queryRight);
			long rightResult = query(mid+1, right, node*2 + 1, queryLeft, queryRight);
			return leftResult + rightResult;
		}
	}

	static void update(int left, int right, int node, int target, long diff) {
		if(target < left || right < target) { // 연관없음
			return;
		} else {
			trees[node] += diff;

			if(left != right) { // 내부 노드
				int mid = (left+right)/2;
				update(left, mid, node*2, target, diff);
				update(mid+1, right, node*2+1, target, diff);
			}
		}
	}
}
