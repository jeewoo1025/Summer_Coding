import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 마법사 상어와 토네이도
public class bj_20057 {
	static int N, curX, curY, curDir=0, answer=0;
	static int[][] map;
	
	// 방향 - 좌,하,우,상
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	
	static int[][] dsx = {{-1,1,-2,-1,1,2,-1,1,0}, {-1,-1,0,0,0,0,1,1,2},    //모래가 퍼지는 x방향
            {1,-1,2,1,-1,-2,1,-1,0}, {1,1,0,0,0,0,-1,-1,-2}};
	static int[][] dsy = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,-1,1,2,-1,1,0},    //모래가 퍼지는 y방향
            {-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0}};
	static int[] sandRatio ={1,1,2,7,7,2,10,10,5};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		printBoard();
		
		// 시작좌표
		curX = N/2;
		curY = N/2;
		
		for(int i=1; i<N; i++) {
			// 길이 i만큼 2번씩 반복
			for(int j=0; j<2; j++) {
				move(curDir, i);  // 한 방향으로 i만큼 이동
				curDir = (curDir+1)%4;
			}
		}
		
		// 좌 방향으로 N-1만큼 이동
		move(0, N-1);
		
		System.out.println(answer);
	}

	// 같은 방향으로 이동 (방향, 길이) - 리턴: 현재방향
	public static void move(int dir, int length) {
		//debug
		System.out.print("시작점 ("+curX+","+curY+")");
		
		for(int i=0; i<length; i++) {
			// 다음 좌표로 이동하기
			curX += dx[dir];
			curY += dy[dir];
			
			int total = map[curX][curY];
			if(total > 0)  // 현재 좌표에서 모래를 흩뿌림
				spread(curX, curY, total);
			map[curX][curY] = 0;
			
			// debug
			System.out.print(" → ("+curX+","+curY+"),버린 모래양:"+answer);
		}
		System.out.println();
		printBoard();
	}
	
	// 모래 흩뿌림 (현재좌표, 모래양) 
	// 주의할 점 : 현재 방향에 따라 모래 뿌리는 비율과 위치가 달라진다!!!!
	public static void spread(int nowX, int nowY, int total) {
		int alpha = total;
		for(int d=0; d<9; d++) {
			int x = nowX + dsx[curDir][d];
			int y = nowY + dsy[curDir][d];
			int spreadAmount = (total*sandRatio[d])/100;
			alpha -= spreadAmount;
			
			if(x<0 || x>=N || y<0 || y>=N) // 격자 밖
				answer += spreadAmount;
			else
				map[x][y] += spreadAmount;
		}
		
		// alpha 계산
		int x = nowX + dx[curDir];
		int y = nowY + dy[curDir];
		if(x<0 || x>=N || y<0 || y>=N) // 격자 밖
			answer += alpha;
		else
			map[x][y] += alpha;
	}
	
	public static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				String str = String.format("%3d", map[i][j]);
				sb.append(str+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
