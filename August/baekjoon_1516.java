import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1516 {
	static int N;
	static ArrayList[] edge;
	static Queue<Integer> queue;
	static int[] indegree;
	static long[] timeTable, answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		indegree = new int[N+1];
		timeTable = new long[N+1];
		answer = new long[N+1];
		
		// edge 초기화
		edge = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			edge[i] = new ArrayList<Integer>();
		}
		
		// 1. 간선 입력
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			timeTable[i] = Long.parseLong(st.nextToken());
			
			int node = Integer.parseInt(st.nextToken());
			while(node != -1) {
				edge[node].add(i);
				indegree[i] += 1;
				
				node = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. 큐 초기화
		queue = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) 
				queue.add(i);
		}
		
		// Queue에서 1개씩 뽑으면서 연결된 간선 뽑기
		while(!queue.isEmpty()) {
			int node = queue.poll();
			answer[node] += timeTable[node];
			System.out.println("curr : "+node+" - 시간 : "+answer[node]);
			
			// 선택된 정점에 부속된 모든 간선에 대해 간선의 수를 감소
			for(int i=0; i<edge[node].size(); i++) {
				int v = (int)edge[node].get(i);
				
				// 선택된 간선 제거
				indegree[v] -= 1;
				
				// 아직 건물을 지을 수 없는 상태
				// 타겟건물의 선행 건설시간 중 MAX 값으로 갱신
				answer[v] = Math.max(answer[v], answer[node]);
				
				if(indegree[v] == 0) {
					queue.add(v);
				}
			}
			
			StringBuilder sb = new StringBuilder("debug \n");
			for(int i=1; i<=N; i++)
				sb.append(answer[i] + " ");
			sb.append("\n");
			System.out.println(sb.toString());
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(answer[i] + "\n");
		System.out.println(sb.toString());
	}
}
