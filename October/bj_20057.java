import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ������ ���� ����̵�
public class bj_20057 {
	static int N, curX, curY, curDir=0, answer=0;
	static int[][] map;
	
	// ���� - ��,��,��,��
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	
	static int[][] dsx = {{-1,1,-2,-1,1,2,-1,1,0}, {-1,-1,0,0,0,0,1,1,2},    //�𷡰� ������ x����
            {1,-1,2,1,-1,-2,1,-1,0}, {1,1,0,0,0,0,-1,-1,-2}};
	static int[][] dsy = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,-1,1,2,-1,1,0},    //�𷡰� ������ y����
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
		
		// ������ǥ
		curX = N/2;
		curY = N/2;
		
		for(int i=1; i<N; i++) {
			// ���� i��ŭ 2���� �ݺ�
			for(int j=0; j<2; j++) {
				move(curDir, i);  // �� �������� i��ŭ �̵�
				curDir = (curDir+1)%4;
			}
		}
		
		// �� �������� N-1��ŭ �̵�
		move(0, N-1);
		
		System.out.println(answer);
	}

	// ���� �������� �̵� (����, ����) - ����: �������
	public static void move(int dir, int length) {
		//debug
		System.out.print("������ ("+curX+","+curY+")");
		
		for(int i=0; i<length; i++) {
			// ���� ��ǥ�� �̵��ϱ�
			curX += dx[dir];
			curY += dy[dir];
			
			int total = map[curX][curY];
			if(total > 0)  // ���� ��ǥ���� �𷡸� ��Ѹ�
				spread(curX, curY, total);
			map[curX][curY] = 0;
			
			// debug
			System.out.print(" �� ("+curX+","+curY+"),���� �𷡾�:"+answer);
		}
		System.out.println();
		printBoard();
	}
	
	// �� ��Ѹ� (������ǥ, �𷡾�) 
	// ������ �� : ���� ���⿡ ���� �� �Ѹ��� ������ ��ġ�� �޶�����!!!!
	public static void spread(int nowX, int nowY, int total) {
		int alpha = total;
		for(int d=0; d<9; d++) {
			int x = nowX + dsx[curDir][d];
			int y = nowY + dsy[curDir][d];
			int spreadAmount = (total*sandRatio[d])/100;
			alpha -= spreadAmount;
			
			if(x<0 || x>=N || y<0 || y>=N) // ���� ��
				answer += spreadAmount;
			else
				map[x][y] += spreadAmount;
		}
		
		// alpha ���
		int x = nowX + dx[curDir];
		int y = nowY + dy[curDir];
		if(x<0 || x>=N || y<0 || y>=N) // ���� ��
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
