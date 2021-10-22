
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj_20056_answer {
	static class Fireball {
		int d,s,m;
		public Fireball(int m, int s, int d) {
			this.d = d;
			this.s = s;
			this.m = m;
		}
	}
	
	static int N,M,K;
	static List<Fireball> map[][];
	
	// ����
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				map[i][j] = new ArrayList<Fireball>();
		
		int r,c,m,s,d;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			map[r][c].add(new Fireball(m,s,d));
		}
		
		for(int k=0; k<K; k++) {
			map = move(map);
		}
		
		// ��� ���� ���ϱ�
		long sum = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j].size()<=0)
					continue;
				
				for(Fireball f:map[i][j])
					sum += f.m;
			}
		}
		System.out.println(sum);
	}
	
	public static int check(int value) {
		// ���� ���̸� �ݴ����
		if(value>N)
			value -= N;
		else if(value<1) 
			value += N;
		return value;
	}

	public static List<Fireball>[][] move(List<Fireball>[][] map) {
		List<Fireball>[][] nextMap = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				nextMap[i][j] = new ArrayList<Fireball>();
		
		// ��� ���̾ �̵�
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j].size() <= 0)
					continue;
				
				for(Fireball now:map[i][j]) {
					int nowSpeed = now.s%N;  // speed�� N���� ũ�� ������ ��쵵 ����!-
					
					// ���� ��ġ���� ������ �������� Sĭ �̵�
					int nr = check(i + dr[now.d]*nowSpeed);
					int nc = check(j + dc[now.d]*nowSpeed);
					
					nextMap[nr][nc].add(new Fireball(now.m, now.s, now.d));
				}
			}
		}
		
		// ��ġ�� �� check
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(nextMap[i][j].size() <= 1)
					continue;
				
				int sumM=0,sumS=0;
				boolean isEven=true, isOdd=true;
				for(Fireball now:nextMap[i][j]) {
					sumM += now.m;
					sumS += now.s;
					
					if(now.d%2==0)
						isOdd = false;
					else
						isEven = true;
				}
				
				sumS /= nextMap[i][j].size();
				sumM /= 5;
				
				nextMap[i][j].clear();
				if(sumM==0)
					continue;
				
				
				if(isEven || isOdd) { // ��� ¦/Ȧ ������
					for(int d=0;d<4;d++)
						nextMap[i][j].add(new Fireball(sumM, sumS, d*2));
				} else {
					for(int d=0;d<4;d++)
						nextMap[i][j].add(new Fireball(sumM, sumS, 1+d*2));
				}
			}
		}
		
		return nextMap;
	}
}
