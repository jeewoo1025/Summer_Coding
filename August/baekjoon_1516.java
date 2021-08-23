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
		
		// edge �ʱ�ȭ
		edge = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			edge[i] = new ArrayList<Integer>();
		}
		
		// 1. ���� �Է�
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
		
		// 2. ť �ʱ�ȭ
		queue = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) 
				queue.add(i);
		}
		
		// Queue���� 1���� �����鼭 ����� ���� �̱�
		while(!queue.isEmpty()) {
			int node = queue.poll();
			answer[node] += timeTable[node];
			System.out.println("curr : "+node+" - �ð� : "+answer[node]);
			
			// ���õ� ������ �μӵ� ��� ������ ���� ������ ���� ����
			for(int i=0; i<edge[node].size(); i++) {
				int v = (int)edge[node].get(i);
				
				// ���õ� ���� ����
				indegree[v] -= 1;
				
				// ���� �ǹ��� ���� �� ���� ����
				// Ÿ�ٰǹ��� ���� �Ǽ��ð� �� MAX ������ ����
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
